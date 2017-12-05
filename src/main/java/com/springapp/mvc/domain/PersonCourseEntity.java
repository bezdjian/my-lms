package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by bezdj on 15/08/2017.
 */
@Entity
@Table(name = "personcourse", schema = "mylms", catalog = "")
public class PersonCourseEntity {
    private int id;
    private int courseid;
    private int personid;
    private String enrollddate;
    private String startdate;
    private String enddate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "courseid")
    public int getCourseId() {
        return courseid;
    }

    public void setCourseId(int courseId) {
        this.courseid = courseId;
    }

    @Basic
    @Column(name = "personid")
    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    @Basic
    @Column(name = "enrolldate")
    public String getEnrollddate() {
        return enrollddate;
    }

    public void setEnrollddate(String enrollddate) {
        this.enrollddate = enrollddate;
    }

    @Basic
    @Column(name = "startdate")
    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate")
    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    //Constructor
    public PersonCourseEntity(){}
    public PersonCourseEntity(int courseid, int personid, String enrollddate, String startdate, String enddate) {
        this.courseid = courseid;
        this.personid = personid;
        this.enrollddate = enrollddate;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonCourseEntity that = (PersonCourseEntity) o;

        if (id != that.id) return false;
        if (courseid != that.courseid) return false;
        if (personid != that.personid) return false;
        if (enrollddate != null ? !enrollddate.equals(that.enrollddate) : that.enrollddate != null) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        return !(enddate != null ? !enddate.equals(that.enddate) : that.enddate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + courseid;
        result = 31 * result + personid;
        result = 31 * result + (enrollddate != null ? enrollddate.hashCode() : 0);
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        return result;
    }
}
