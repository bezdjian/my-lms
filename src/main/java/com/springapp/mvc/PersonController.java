package com.springapp.mvc;

import com.springapp.mvc.dao.PersonCourseDao;
import com.springapp.mvc.dao.PersonDao;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Scope("session")
@RequestMapping("/")
public class PersonController {

	@Autowired
	private PersonDao personDao;

	@Autowired
	PersonProductDao personProductDao;

	@Autowired
	PersonCourseDao personCourseDao;

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


	@RequestMapping(value = "/allusers")
	public String allUsers(HttpServletRequest request, Model model){
		List<PersonEntity> allusers = personDao.getAllUsers();
		model.addAttribute("allusers", allusers);
		return "allusers";
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