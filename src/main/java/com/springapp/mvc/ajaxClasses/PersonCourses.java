package com.springapp.mvc.ajaxClasses;

/**
 * Created by harout on 2017-06-05.
 */
public class PersonCourses {

    private String firstname;
    private String lastname;
    private String coursename;

    public PersonCourses(){}

    public PersonCourses(String firstname, String lastname, String coursename) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.coursename = coursename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

}
