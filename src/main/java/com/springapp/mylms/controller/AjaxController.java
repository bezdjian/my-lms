package com.springapp.mylms.controller;

import com.springapp.mylms.ajaxClasses.Greeting;
import com.springapp.mylms.ajaxClasses.PersonCourses;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.domain.PersonEntity;
import com.springapp.mylms.domain.PersonProductEntity;
import com.springapp.mylms.helpers.CryptoUtils;
import com.springapp.mylms.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PersonService personService;

    @Autowired
    PersonProductRepository personProductDao;

    @GetMapping(value = "/getSearchResult")
    @ResponseBody
    public ArrayList<PersonCourses> getSearchResultViaAjax(@RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname) {
        ArrayList<PersonCourses> courses = new ArrayList<PersonCourses>();
        courses.add(new PersonCourses(firstname, lastname, "coursename3"));
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
    public String checkout(@RequestParam("personid") int personid,
            @RequestParam("productids") List<Integer> productids) {

        for (Integer productId : productids) {
            PersonProductEntity personProductEntity = new PersonProductEntity();
            personProductEntity.setPersonid(personid);
            personProductEntity.setProductid(productId);
            personProductDao.save(personProductEntity);
        }
        return "done";
    }

    //TEST
    @GetMapping(value = "greeting")
    public Greeting greeting(@RequestParam("name") String name, Model m, HttpServletResponse respons) {
        String message = "Hello, %s!";
        return new Greeting(String.format(message, name), "lastname");
        /*
        try{
            respons.sendRedirect("home");
        }catch (IOException io){
            System.out.print("------------------------------------------------------------------------------" + io
            .getMessage());
        }
        */
    }
}
