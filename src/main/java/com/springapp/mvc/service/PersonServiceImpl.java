package com.springapp.mvc.service;

import com.springapp.mvc.dao.PersonDao;
import com.springapp.mvc.pojo.Login;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.pojo.UserReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public void insertPerson(PersonEntity person) {
        personDao.insertPerson(person);
    }

    @Override
    public PersonEntity validateUser(Login login) {
       return personDao.validateUser(login);
    }

    @Override
    public PersonEntity getUserInfo(String username, String password){ return personDao.getUserInfo(username, password); }

    @Override
    public List<PersonEntity> getAllUsers() {
        return personDao.getAllUsers();
    }

    @Override
    public List<PersonEntity> getAllUnerolledUsers(int courseid) {
        return personDao.getAllUnerolledUsers(courseid);
    }

    @Override
    public List<PersonEntity> getAllEnrolledUsers(int courseid) {
        return personDao.getAllEnrolledUsers(courseid);
    }

    @Override
    public PersonEntity getUserById(int userid) {
        return personDao.getUserById(userid);
    }

    @Override
    public void removeUser(int userid) {
        personDao.removeUser(userid);
    }

    @Override
    public List<UserReport> getUserReports() {
        return personDao.getUserReports();
    }
}
