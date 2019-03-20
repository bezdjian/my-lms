package com.springapp.mvc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
@RequestMapping("/")
public class ErrorController {

	@RequestMapping("/error/{errormsg}")
	public ModelAndView printWelcome(ModelMap model, HttpServletRequest request,
									 @PathVariable("errormsg") String errormsg) {
		model.addAttribute("errormsg", errormsg);
		return new ModelAndView("error");
	}
}