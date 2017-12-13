package com.springapp.mvc.domain;

public class UserReport {
    private int personid;
    private String fullname;
    private String coursename;
    private String role;
    private String enddate;

    public UserReport(int personid, String fullname, String role, String coursename, String enddate, String enrolldate, String startdaate) {
        this.personid = personid;
        this.fullname = fullname;
        this.role = role;
        this.coursename = coursename;
        this.enddate = enddate;
        this.enrolldate = enrolldate;
        this.startdaate = startdaate;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(String enrolldate) {
        this.enrolldate = enrolldate;
    }

    public String getStartdaate() {
        return startdaate;
    }

    public void setStartdaate(String startdaate) {
        this.startdaate = startdaate;
    }

    private String enrolldate;
    private String startdaate;
}
