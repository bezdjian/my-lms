package com.springapp.mylms.controller;

import com.springapp.mylms.entity.PersonEntity;
import com.springapp.mylms.helpers.CryptoUtils;
import com.springapp.mylms.repository.PersonCourseRepository;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@Scope("session")
@RequestMapping("/")
@Slf4j
public class MainController {

    final PersonProductRepository personProductRepository;
    final PersonCourseRepository personCourseRepository;
    private final PersonRepository personRepository;

    public MainController(PersonRepository personRepository,
            PersonProductRepository personProductRepository, PersonCourseRepository personCourseRepository) {
        this.personRepository = personRepository;
        this.personProductRepository = personProductRepository;
        this.personCourseRepository = personCourseRepository;
    }

    @GetMapping("/")
    public ModelAndView printWelcome(ModelMap model, HttpServletRequest request) {
        model.addAttribute("message", "Registration");

        String messageErr = (String) request.getSession().getAttribute("message_err");
        if (messageErr != null) {
            model.addAttribute("message_err", messageErr);
        }

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("userToBeRegistered", new PersonEntity());

        return mav;
        //return "registration";
    }

    @PostMapping(value = "/registerProcess")
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model m,
            @ModelAttribute("userToBeRegistered") PersonEntity user) {
        try {
            //Encrypting user's password before insertion.
            String password = user.getPassword();
            String hash = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(password));
            user.setPassword(hash);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        personRepository.save(user);
        request.getSession().setAttribute("person", user);

        //This return keeps the /registerProcess on the URL, when refresh, it gets registered again.
        //so i need to redirect to home.
        m.addAttribute("mylmstitle", "Home");
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String homePage(HttpServletRequest request, Model m, @ModelAttribute("homeview") ModelAndView mav) {
        if (request.getSession().getAttribute("person") != null) {
            PersonEntity personInSession = (PersonEntity) request.getSession().getAttribute("person");
            //Reload person
            PersonEntity person = personRepository.getOne(personInSession.getId());
            // TODO: GET Products and Courses.
            //List<ProductEntity> personProducts = personProductRepository.getAllPersonProducts(personInSession.getId
            // ());
            //List<PersonCourseObject> personCourses =
            //      personCourseRepository.getAllPersonCourses(personInSession.getId());

            m.addAttribute("personProducts", Collections.emptyList());
            m.addAttribute("personCourses", Collections.emptyList());
            m.addAttribute("person", person);

            //remove message_err
            request.getSession().setAttribute("message_err", null);
            m.addAttribute("mylmstitle", "Home");
            return "home";
        }
        return "redirect:/";

    }

    //Had to put the same function here for the registration page that goes from this controller.
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