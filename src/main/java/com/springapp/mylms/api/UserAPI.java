package com.springapp.mylms.api;

import com.springapp.mylms.repository.PersonCourseRepository;
import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.entity.PersonEntity;
import com.springapp.mylms.entity.ProductEntity;
import com.springapp.mylms.modal.PersonCourseObject;
import com.springapp.mylms.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by bezdj on 21/11/2017.
 */

@RestController
@RequestMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/myLMS
// /rest/user/getallusers
public class UserAPI {

    final PersonProductRepository personProductDao;
    final PersonService personService;
    final PersonCourseRepository personCourseDao;

    public UserAPI(PersonProductRepository personProductDao,
            PersonService personService, PersonCourseRepository personCourseDao) {
        this.personProductDao = personProductDao;
        this.personService = personService;
        this.personCourseDao = personCourseDao;
    }

    @GetMapping(value = "/getallusers")
    @ResponseBody
    public List<PersonEntity> getAllUsers() {
        return personService.getAllUsers();
    }

    @GetMapping(value = "/getuserproducts")
    @ResponseBody
    public List<ProductEntity> getUserProducts(@RequestParam(value = "userid") Long userid) {
        // return personProductDao.findAllByPersonid(userid);
        return Collections.emptyList();
    }

    @GetMapping(value = "/getusercourses")
    @ResponseBody
    public List<PersonCourseObject> getUserCourses(@RequestParam(value = "userid") Long userid) {
        // return personCourseDao.findPersonCourseEntitiesByPersonid(userid);
        return Collections.emptyList();
    }
}
