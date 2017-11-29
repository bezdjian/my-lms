package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by bezdj on 15/08/2017.
 */
@Entity
@Table(name = "course", schema = "registration", catalog = "")
public class CourseEntity {
    private int id;
    private String coursename;
    private String description;
    private String idnumber;
    private String courseimage;
    private int categoryid;

    public String getCourseimage() {
        return courseimage;
    }

    public void setCourseimage(String courseImage) {
        this.courseimage = courseImage;
    }

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
    @Column(name = "coursename")
    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "idnumber")
    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    @Basic
    @Column(name = "categoryid")
    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    //Constructor
    public CourseEntity(){}
    public CourseEntity(int id, String coursename, String description, String idnumber, int categoryid, String courseimage) {
        this.id = id;
        this.coursename = coursename;
        this.description = description;
        this.idnumber = idnumber;
        this.categoryid = categoryid;
        this.courseimage = courseimage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (id != that.id) return false;
        if (categoryid != that.categoryid) return false;
        if (coursename != null ? !coursename.equals(that.coursename) : that.coursename != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return !(idnumber != null ? !idnumber.equals(that.idnumber) : that.idnumber != null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (coursename != null ? coursename.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
        result = 31 * result + (courseimage != null ? courseimage.hashCode() : 0);
        result = 31 * result + categoryid;
        return result;
    }
}
