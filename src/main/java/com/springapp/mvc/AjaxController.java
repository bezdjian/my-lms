package com.springapp.mvc;

import com.springapp.mvc.ajaxClasses.Greeting;
import com.springapp.mvc.ajaxClasses.PersonCourses;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.PersonProductEntity;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by harout on 2017-06-05.
 */

@RestController
public class AjaxController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonProductDao personProductDao;

    @RequestMapping(value = "/getSearchResult", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<PersonCourses> getSearchResultViaAjax(@RequestParam("firstname") String firstname,
                                                     @RequestParam("lastname") String lastname) {
        ArrayList<PersonCourses> courses = new ArrayList<PersonCourses>();
        courses.add(new PersonCourses(firstname, lastname, "coursename3"));
        return courses;
    }

    @RequestMapping(value = "/getSearchResult1", method = RequestMethod.GET)
    @ResponseBody
    public PersonEntity getUserInfoViaAjax(@RequestParam("username") String username,
                                                           @RequestParam("password") String password) {
        //ArrayList<PersonCourses> courses = new ArrayList<PersonCourses>();
        return personService.getUserInfo(username, password);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @ResponseBody
    public String checkout(@RequestParam("personid") int personid, @RequestParam("productids") ArrayList<Integer> productids){

        for(Integer productId : productids){
            PersonProductEntity personProductEntity = new PersonProductEntity();
            personProductEntity.setPersonid(personid);
            personProductEntity.setProductid(productId);
            personProductDao.insertPersonProduct(personProductEntity);
        }

        return "done";
    }

    //TEST
    private static final String message = "Hello, %s!";
    @RequestMapping(value = "greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam("name") String name, Model m, HttpServletResponse respons){
        return new Greeting(String.format(message, name), "lastname");
        /*
        try{
            respons.sendRedirect("home");
        }catch (IOException io){
            System.out.print("------------------------------------------------------------------------------" + io.getMessage());
        }
        */
    }
}
