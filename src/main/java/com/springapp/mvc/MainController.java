package com.springapp.mvc;

import com.springapp.mvc.dao.*;
import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.PersonCourseEntity;
import com.springapp.mvc.domain.PersonEntity;

import com.springapp.mvc.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public String printName(HttpServletRequest request, Model m, @ModelAttribute("registeredUser") PersonEntity registeredUser,
							@ModelAttribute("homeview") ModelAndView mav){
		PersonEntity person = (PersonEntity) request.getSession().getAttribute("person");
		List<ProductEntity> personProducts = personProductDao.getAllPersonProducts(person.getId());
		List<CourseEntity> personCourses = personCourseDao.getAllPersonCourses(person.getId());

		m.addAttribute("personProducts", personProducts);
		m.addAttribute("personCourses", personCourses);

		//remove message_err
		request.getSession().setAttribute("message_err", null);
		return "home";
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