package com.springapp.mylms.controller;

/**
 * Created by bezdj on 05/06/2017.
 */

import com.springapp.mylms.entity.PersonEntity;
import com.springapp.mylms.helpers.CryptoUtils;
import com.springapp.mylms.modal.Login;
import com.springapp.mylms.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@Slf4j
public class LoginController {

    final PersonService userService;

    public LoginController(PersonService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("login", new Login());
        return mav;
    }

    @PostMapping(value = "/loginProcess")
    public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute("login") Login login, Model modal) {
        ModelAndView mav = null;

        try {
            //Get user's password and hash it to make the sql query.
            String password = login.getPassword();
            String hash = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(password));
            login.setPassword(hash);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        PersonEntity user = userService.validateUser(login.getUsername(), login.getPassword());

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
    public Map<String, String> populateCountryNames() {
        Map<String, String> countryNames = new LinkedHashMap<String, String>();
        countryNames.put("Sweden", "Sweden");
        countryNames.put("Denmark", "Denmark");
        countryNames.put("Norway", "Norway");
        countryNames.put("Finland", "Finland");
        return countryNames;
    }
}