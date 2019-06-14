package com.springapp.mvc.controller;

/**
 * Created by bezdj on 05/06/2017.
 */

import com.springapp.mvc.pojo.Login;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.helpers.CryptoUtils;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    PersonService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") Login login, Model modal) {
        ModelAndView mav = null;

        try{
            //Get user's password and hash it to make the sql query.
            String password = login.getPassword();
            String hash = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(password));
            login.setPassword(hash);

        }catch(Exception e){
            e.printStackTrace();
        }

        PersonEntity user = userService.validateUser(login);

        if (null != user) {
            mav = new ModelAndView(new RedirectView("home")); // RedirectView to change the URL to /home.
            //mav.addObject("person", user);

            //This one is working now..
            request.getSession().setAttribute("person", user);
        } else {
            mav = new ModelAndView(new RedirectView(request.getContextPath() + "/"));
            mav.addObject("message_err", "Username or Password is wrong!!");

            //Using this now..
            modal.addAttribute("message_err", "Username or password is invalid!!!!");
            request.getSession().setAttribute("message_err", "Username or Password is invalid!");
        }

        return mav;
    }

    //Had to put the same function here since the registration MainController has it but in case of wrong
    //login, the page refreshes from this controller and needs countryNames.
    //Maybe puthing this on the Session would work.
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