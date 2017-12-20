package com.springapp.mvc;

import au.com.bytecode.opencsv.CSVReader;
import com.springapp.mvc.dao.*;
import com.springapp.mvc.domain.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Statement;
import java.util.*;

@Controller
@Scope("session")
@RequestMapping("/")
public class MainController {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	PersonProductDao personProductDao;

	@Autowired
	PersonCourseDao personCourseDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping("/")
	public ModelAndView printWelcome(ModelMap model, HttpServletRequest request) {
		model.addAttribute("message", "Registration");

		String message_err = (String) request.getSession().getAttribute("message_err");
		if(message_err != null){
			model.addAttribute("message_err", message_err);
		}

		ModelAndView mav = new ModelAndView("registration");
		mav.addObject("userToBeRegistered", new PersonEntity());

		return mav;
		//return "registration";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, HttpServletResponse response,
								@ModelAttribute("userToBeRegistered") PersonEntity user) {

		personDao.insertPerson(user);
		request.getSession().setAttribute("person", user);

		//This return keeps the /registerProcess on the URL, when refresh, it gets registered again.
		//so i need to redirect to home.
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String printName(HttpServletRequest request, Model m, @ModelAttribute("homeview") ModelAndView mav){
		PersonEntity personInSession = (PersonEntity) request.getSession().getAttribute("person");
		//Reload person
		//PersonEntity person = personDao.getUserById(personInSession.getId());
		List<ProductEntity> personProducts = personProductDao.getAllPersonProducts(personInSession.getId());
		List<CourseEntity> personCourses = personCourseDao.getAllPersonCourses(personInSession.getId());

		m.addAttribute("personProducts", personProducts);
		m.addAttribute("personCourses", personCourses);
		//m.addAttribute("person", person);

		//remove message_err
		request.getSession().setAttribute("message_err", null);
		return "home";
	}

	@RequestMapping("/profile/{userid}")
	public String userProfile(HttpServletRequest request, Model model, @PathVariable("userid") int userid){
		//reload user with userid.
		PersonEntity user = personDao.getUserById(userid);
		model.addAttribute("person", user);
		return "profile";
	}

	@RequestMapping("/editprofile/{userid}/{edit}")
	public String userProfileEdit(HttpServletRequest request, Model model,
							  @PathVariable("userid") int userid, @PathVariable("edit") String edit,
								  @ModelAttribute("adduser") PersonEntity adduser){

		if(edit.equals("doedit")){
			//Edit person from form.
			PersonEntity editUser = new PersonEntity();
			editUser.setId(userid);
			editUser.setUsername(request.getParameter("person_uname"));
			editUser.setPassword(request.getParameter("person_password"));
			editUser.setFirstname(request.getParameter("person_fname"));
			editUser.setLastname(request.getParameter("person_lname"));
			editUser.setGender(request.getParameter("gender"));
			editUser.setCountry(request.getParameter("country"));
			editUser.setEmail(request.getParameter("person_email"));
			editUser.setCompanyname(request.getParameter("person_company"));
			editUser.setCompanylocation(request.getParameter("person_clocation"));
			editUser.setCompanyservices(request.getParameter("person_cservices"));
			editUser.setRole(request.getParameter("role"));
			editUser.setAccounttype(request.getParameter("accounttype"));
			personDao.insertPerson(editUser);
			//Return back to viewprofile
			return "redirect:/profile/"+userid;
		}else if(edit.equals("doadd")){
			System.out.println("NEW USER: " + adduser.getFirstname());
			personDao.insertPerson(adduser);
			//Return back to all users
			return "redirect:/allusers";
		}

		PersonEntity user = personDao.getUserById(userid);
		model.addAttribute("person", user);
		return "editprofile";
	}

	@RequestMapping(value = "/reports/{type}")
	public String showUserReports(HttpServletRequest request, @PathVariable("type") String type,
								  Model model){
		//type is the report type, users, courses etx...

		if(type.equals("usercourses")){
			//get report for users
			List userCoursesReport = personDao.getUserReports();
			model.addAttribute("userCoursesReport", userCoursesReport);
		}else if(type.equals("users")){
			//get report for users only
			List userReport = personDao.getAllUsers();
			model.addAttribute("userReport", userReport);
		}else if(type.equals("courses")){
			//get report for courses
		}

		//sending to page reports, but model.addAttribute differs by the type
		return "reports";
	}

	@RequestMapping(value = "/downloadCSV/{type}")
	public void downloadCSV(HttpServletResponse response, @PathVariable("type") String reportType) throws IOException {

		String reportName = "";
		if(reportType.equals("usercourses")){
			reportName = "UserCourses_Report.csv";
			ArrayList<String> rows = new ArrayList<String>();
			//Add headers
			rows.add("ID, Full name, Role, Course name, End date, Enrol date, Start date");
			rows.add("\n");

			List<UserReport> report = personDao.getUserReports();

			//First add them to list of rows
			for(int i=0; i<report.size(); i++){
				UserReport reportRow = report.get(i);
				rows.add(reportRow.getPersonid()+","+reportRow.getFullname()+","+reportRow.getRole()+","+reportRow.getCoursename()+","+reportRow.getEnddate()+","+reportRow.getEnrolldate()+","+reportRow.getStartdaate());
				rows.add("\n");
			}

			//Now insert each rows to output.
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}

		}else if(reportType.equals("users")){
			reportName = "User_Report.csv";
			ArrayList<String> rows = new ArrayList<String>();
			//Add headers
			rows.add("ID, Full name, Role, Email, Country");
			rows.add("\n");

			List<PersonEntity> report = personDao.getAllUsers();

			//First add them to list of rows
			for(int i=0; i<report.size(); i++){
				PersonEntity reportRow = report.get(i);
				rows.add(reportRow.getId()+","+reportRow.getFirstname()+" "+reportRow.getLastname()+","+reportRow.getRole()+","+reportRow.getEmail()+","+reportRow.getCountry());
				rows.add("\n");
			}

			//Now insert each rows to output.
			Iterator<String> iter = rows.iterator();
			while (iter.hasNext()) {
				String outputString = (String) iter.next();
				response.getOutputStream().print(outputString);
			}

		}

		response.setContentType("text/csv");
		response.setHeader("Content-disposition", "attachment;filename="+reportName);

		response.getOutputStream().flush();

	}

	@RequestMapping(value = "/allusers")
	public String allUsers(HttpServletRequest request, Model model){
		List<PersonEntity> allusers = personDao.getAllUsers();
		model.addAttribute("allusers", allusers);
		return "allusers";
	}

	//Dont know, should be another way than this.
	@RequestMapping(value = "/uploadUsersForm")
	public String uploadUsers(HttpServletRequest request, Model model){
		return "uploadUsers";
	}

	@RequestMapping(value = "/uploadUsers")
	public String uploadUsers(HttpServletRequest request, Model model, @RequestParam("userCSV") MultipartFile file){
		System.out.println("FILE NAME: " + file.getOriginalFilename());
		if (file.isEmpty()) {
			model.addAttribute("error_msg", "failed to upload file because its empty");
			System.out.println("----------------failed to upload file because its empty");
			return "uploadUsers";
		}

		//Saves the file under /target/myLMS/uploadedfile
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		File dir = new File(rootPath + File.separator + "uploadedfile");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());

		try {
			try (InputStream is = file.getInputStream();
				 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
				int i;
				//write file to server
				while ((i = is.read()) != -1) {
					stream.write(i);
				}
				stream.flush();
			}
		} catch (IOException e) {
			model.addAttribute("error_msg", "failed to process file because : " + e.getMessage());
			e.printStackTrace();
			return "uploadUsers";
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(serverFile));
			String line = "";
			int iter = 0;
			while((line = br.readLine()) != null){
				if(iter == 0){
					iter++;
				}else{
					try{
						String[] values = line.split(",");
						PersonEntity newuser = new PersonEntity();
						newuser.setUsername(values[0]);
						newuser.setEmail(values[2]);
						newuser.setFirstname(values[3]);
						newuser.setLastname(values[4]);
						newuser.setCountry("Sweden");
						newuser.setCompanyname("SIDA");

						//Insert the newuser
						personDao.insertPerson(newuser);
					}catch (ArrayIndexOutOfBoundsException ae){
						System.out.println("ArrayIndexOutOfBoundsException: " + ae.getMessage());
						model.addAttribute("error_msg", "Value was not found on column number " + ae.getMessage());
						ae.printStackTrace();
						return "uploadUsers";
					}
				}
			}


		} catch (IOException e) {
			System.out.println("error while reading csv and put to db : " + e.getMessage());
			model.addAttribute("error while reading csv and put to db : " + e.getMessage());
			return "uploadUsers";
		}

		model.addAttribute("success_msg", "success upload and process file");
		return "uploadUsers";
	}

	@RequestMapping(value = "/adduser")
	public String addUser(HttpServletRequest request, Model model){
		PersonEntity adduser = new PersonEntity();
		model.addAttribute("adduser", adduser);
		return "adduser";
	}

	//Had to put the same function here for the registration page that goes from this controller.
	@ModelAttribute("countryNames")
	public Map<String,String> populateCountryNames() {
		Map<String,String> countryNames = new LinkedHashMap<String,String>();
		countryNames.put("Sweden", "Sweden");
		countryNames.put("Denmark", "Denmark");
		countryNames.put("Norway", "Norway");
		countryNames.put("Finland", "Finland");
		return countryNames;
	}
}