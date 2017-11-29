package com.springapp.mvc.api;

import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.PersonProductEntity;
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
produces = MediaType.APPLICATION_JSON_VALUE)
public class UserAPI {

    @Autowired
    PersonProductDao personProductDao;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonEntity> getAllUsers(){
        return personService.getAllUsers();
    }

    @RequestMapping(value = "/getuserproducts", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductEntity> getUserProducts(@RequestParam(value = "userid") int userid){
        return personProductDao.getAllPersonProducts(userid);
    }
}
