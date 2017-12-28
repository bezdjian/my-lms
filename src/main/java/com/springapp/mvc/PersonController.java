package com.springapp.mvc;

import com.springapp.mvc.dao.CourseDao;
import com.springapp.mvc.dao.PersonCourseDao;
import com.springapp.mvc.dao.PersonDao;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.UserReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/")
public class PersonController {

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
			//Add headers, Stargin columns with capital ID make SYLK error in Excell.
			rows.add("id, Full name, Role, Course name, End date, Enrol date, Start date");
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
			//Add headers, Stargin columns with capital ID make SYLK error in Excell.
			rows.add("id, Full name, Role, Email, Country");
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
				 BufferedWriter stream = new BufferedWriter(new OutputStreamWriter(
				 		new FileOutputStream(serverFile), StandardCharsets.UTF_8))) {
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
		//EO: file save on the server

		ICsvBeanReader beanReader = null;
		try{
			beanReader = new CsvBeanReader(new FileReader(serverFile),
					CsvPreference.STANDARD_PREFERENCE);

			final String[] headers = beanReader.getHeader(true);
			final CellProcessor[] processor = getProcessors();

			PersonEntity person;
			while ((person = beanReader.read(PersonEntity.class, headers, processor)) != null){
				//Insert person, person = each row of the CSV file.
				personDao.insertPerson(person);
			}

		}catch (IOException fe){
			fe.printStackTrace();
			model.addAttribute("error_msg", fe.getMessage());
			return "uploadUsers";
		}catch (SuperCsvCellProcessorException csve){
			csve.printStackTrace();
			model.addAttribute("error_msg", csve.getMessage());
			return "uploadUsers";
		}finally {
			if(beanReader != null){
				try{
					beanReader.close();
				}catch (IOException ioe){
					ioe.printStackTrace();
				}
			}
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

	@RequestMapping(value = "delete/{userid}")
	public String deleteUser(@PathVariable("userid") int userid, RedirectAttributes ra){
		personDao.removeUser(userid);
		//When return "redirect:/...", we need RedirectAttributes and addFlashAttribute
		ra.addFlashAttribute("delete_msg", "User with " + userid + " has been deleted.");
		return "redirect:/allusers";
	}

	private static CellProcessor[] getProcessors(){
		return new CellProcessor[] {
				new NotNull(), // username
				new NotNull(), //email
				new NotNull(), //firstname
				new NotNull(), //lastname
				new Optional(), //country
				new Optional(), //companyname
				new Optional(), //companylocation
				new Optional(), //companyservices
				new Optional()}; //password
	}

	/*
	//Had to put the same function here for the registration page that goes from this controller.
	@ModelAttribute("countryNames")
	public Map<String,String> populateCountryNames() {
		Map<String,String> countryNames = new LinkedHashMap<String,String>();
		countryNames.put("Sweden", "Sweden");
		countryNames.put("Denmark", "Denmark");
		countryNames.put("Norway", "Norway");
		countryNames.put("Finland", "Finland");
		return countryNames;
	}*/
}