package com.springapp.mvc.dao;

import com.springapp.mvc.domain.Login;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.PersonProductEntity;
import com.springapp.mvc.domain.UserReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */
public interface PersonDao {
    void insertPerson(PersonEntity person);
    PersonEntity validateUser(Login login);
    PersonEntity getUserInfo(String username, String password);
    List<PersonEntity> getAllUsers();
    //Returns users who are NOT enrolled to this course.
    List<PersonEntity> getAllUnerolledUsers(int courseid);
    //Returns users who are enrolled to this course
    List<PersonEntity> getAllEnrolledUsers(int courseid);
    PersonEntity getUserById(int userid);

    void removeUser(int userid);
    //reports, users with courses
    List<UserReport> getUserReports();
}
