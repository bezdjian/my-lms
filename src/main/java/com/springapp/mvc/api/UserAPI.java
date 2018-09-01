package com.springapp.mvc.api;

import com.springapp.mvc.dao.PersonCourseDao;
import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonCourseObject;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.ProductEntity;
import com.springapp.mvc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bezdj on 21/11/2017.
 */

@RestController
@RequestMapping(value = "/rest/user", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE) // http://localhost:8080/myLMS/rest/user/getallusers
public class UserAPI {

    @Autowired
    PersonProductDao personProductDao;

    @Autowired
    PersonService personService;

    @Autowired
    PersonCourseDao personCourseDao;

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<PersonEntity> getAllUsers() {
        return personService.getAllUsers();
    }

    @RequestMapping(value = "/getuserproducts", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ProductEntity> getUserProducts(@RequestParam(value = "userid") int userid) {
        return personProductDao.getAllPersonProducts(userid);
    }

    @RequestMapping(value = "/getusercourses", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<PersonCourseObject> getUserCourses(@RequestParam(value = "userid") int userid) {
        return personCourseDao.getAllPersonCourses(userid);
    }
}
