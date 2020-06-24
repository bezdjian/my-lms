package com.springapp.mylms.controller;

import com.springapp.mylms.ajaxClasses.Greeting;
import com.springapp.mylms.ajaxClasses.PersonCourses;
import com.springapp.mylms.entity.PersonEntity;
import com.springapp.mylms.entity.PersonProductEntity;
import com.springapp.mylms.helpers.CryptoUtils;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.service.PersonService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harout on 2017-06-05.
 */

@RestController
public class AjaxController {

    final PersonService personService;
    final PersonProductRepository personProductDao;

    public AjaxController(PersonService personService,
            PersonProductRepository personProductDao) {
        this.personService = personService;
        this.personProductDao = personProductDao;
    }

    @GetMapping(value = "/getSearchResult")
    @ResponseBody
    public List<PersonCourses> getSearchResultViaAjax(@RequestParam("firstname") String firstName,
            @RequestParam("lastName") String lastName) {
        List<PersonCourses> courses = new ArrayList<>();
        courses.add(new PersonCourses(firstName, lastName, "coursename3"));
        return courses;
    }

    @GetMapping(value = "/getSearchResult1")
    @ResponseBody
    public PersonEntity getUserInfoViaAjax(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        //ArrayList<PersonCourses> courses = new ArrayList<PersonCourses>();
        String hashedPassword = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(password));
        return personService.validateUser(username, hashedPassword);
    }

    @GetMapping(value = "/checkout")
    @ResponseBody
    public String checkout(@RequestParam("personid") Long personId,
            @RequestParam("productids") List<Long> productIds) {

        for (Long productId : productIds) {
            PersonProductEntity personProductEntity = new PersonProductEntity();
            personProductEntity.setPersonId(personId);
            personProductEntity.setProductId(productId);
            personProductDao.save(personProductEntity);
        }
        return "done";
    }

    //TEST
    @GetMapping(value = "greeting")
    public Greeting greeting(@RequestParam("name") String name, Model m, HttpServletResponse respons) {
        String message = "Hello, %s!";
        return new Greeting(String.format(message, name), "lastname");
    }
}
