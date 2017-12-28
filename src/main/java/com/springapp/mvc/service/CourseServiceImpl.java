package com.springapp.mvc.service;

import com.springapp.mvc.dao.CourseDao;
import com.springapp.mvc.domain.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void insertCourse(CourseEntity course) {
        courseDao.insertCourse(course);
    }

    @Override
    public CourseEntity getCourseById(int id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public List<CourseEntity> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void removeCourse(int courseid) {
        courseDao.removeCourse(courseid);
    }
}
