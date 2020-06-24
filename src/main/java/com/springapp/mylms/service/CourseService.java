package com.springapp.mylms.service;

import com.springapp.mylms.repository.CourseRepository;
import com.springapp.mylms.domain.CourseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void insertCourse(CourseEntity course) {
        courseRepository.save(course);
    }

    public CourseEntity getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public void removeCourse(Long courseid) {
        courseRepository.deleteById(courseid);
    }
}
