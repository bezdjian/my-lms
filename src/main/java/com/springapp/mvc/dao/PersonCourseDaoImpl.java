package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.PersonCourseEntity;
import com.springapp.mvc.domain.PersonEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by bezdj on 15/08/2017.
 */

@Repository
public class PersonCourseDaoImpl implements PersonCourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    //Override functions
    @Override
    public void insertPersonCourse(PersonCourseEntity personCourseEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(personCourseEntity);
    }

    @Override
    public CourseEntity getPersonCourseByPersonId(int personid) {
        /*String sql = "SELECT * FROM course WHERE id = (SELECT courseid FROM personcourse WHERE personid = " + personid +" )";
        List<CourseEntity> course = jdbcTemplate.query(sql, new CourseMapper());
        return course.size() > 0 ? course.get(0) : new CourseEntity();*/
        return null; //We might not need this function..
    }

    @Override
    public List<CourseEntity> getAllPersonCourses(int personid) {
        String sql = "SELECT * FROM course WHERE id IN (SELECT courseid FROM personcourse WHERE personid = " + personid +" )";
        List<CourseEntity> course = jdbcTemplate.query(sql,
                (rs, rowNum)->new CourseEntity(rs.getInt("id"), rs.getString("coursename"), rs.getString("description"),
                        rs.getString("idnumber"), rs.getInt("categoryid") , rs.getString("courseimage")));
        return course.size() > 0 ? course :  Collections.emptyList();
    }

    @Transactional
    @Override
    public void enrolUserToCourse(int courseid, int userid) {
        String enrollDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        PersonCourseEntity personCourse = new PersonCourseEntity(courseid, userid, enrollDate, "", "");
        insertPersonCourse(personCourse);

    }
}


