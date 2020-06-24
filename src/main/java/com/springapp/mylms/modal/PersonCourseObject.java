package com.springapp.mylms.modal;

/**
 * Created by bezdj on 25/12/2017.
 */
public class PersonCourseObject {
    private int id;
    private int categoryid;
    private String courseimage;
    private String coursename;
    private String description;
    private String idnumber;
    private String enrollddate;
    private String startdate;
    private String enddate;

    public PersonCourseObject(int id, int categoryid, String courseimage, String coursename, String description, String idnumber, String enrollddate, String startdate, String enddate) {
        this.id = id;
        this.categoryid = categoryid;
        this.courseimage = courseimage;
        this.coursename = coursename;
        this.description = description;
        this.idnumber = idnumber;
        this.enrollddate = enrollddate;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCourseimage() {
        return courseimage;
    }

    public void setCourseimage(String courseimage) {
        this.courseimage = courseimage;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEnrollddate() {
        return enrollddate;
    }

    public void setEnrollddate(String enrollddate) {
        this.enrollddate = enrollddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
