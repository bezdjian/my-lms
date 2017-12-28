package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.PersonCourseEntity;
import com.springapp.mvc.domain.PersonCourseObject;

import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */
public interface PersonCourseDao {
    void insertPersonCourse(PersonCourseEntity personCourseEntity);
    CourseEntity getPersonCourseByPersonId(int id);
    List<PersonCourseObject> getAllPersonCourses(int personid);
    void enrolUserToCourse(int courseid, int userid);
}
