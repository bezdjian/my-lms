package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.Login;
import com.springapp.mvc.domain.PersonCourseEntity;
import com.springapp.mvc.domain.PersonEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */
public interface CourseDao {
    void insertCourse(CourseEntity course);
    CourseEntity getCourseById(int id);
    List<CourseEntity> getAllCourses();
    void removeCourse(int courseid);
}