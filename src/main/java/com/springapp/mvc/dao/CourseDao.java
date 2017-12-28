package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;

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