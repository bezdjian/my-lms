package com.springapp.mylms.repository;

import com.springapp.mylms.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bezdj on 04/02/2017.
 */

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByUsernameAndPassword(String username, String password);
    //Returns users who are NOT enrolled to this course.

    /**
     * public List<PersonEntity> getAllUnerolledUsers(int courseid) {
     * List<PersonEntity> users = jdbcTemplate.query("SELECT * FROM person WHERE id NOT IN (SELECT personid
     * FROM personcourse WHERE courseid = " +courseid+ ")",
     * (rs, rowNum)->new PersonEntity(rs.getInt("id"), rs.getString("username"), rs.getString
     * ("firstname"), rs.getString("lastname"),
     * rs.getString("country"), rs.getString("email"), rs.getString("gender"),
     * rs.getString("accounttype"), rs.getString("companyname"), rs.getString
     * ("companylocation"),
     * rs.getString("companyservices"), rs.getString("role"), rs.getString("profileImage")));
     * return users;
     * }
     *
     * @Override public List<PersonEntity> getAllEnrolledUsers(int courseid) {
     * List<PersonEntity> users = jdbcTemplate.query("SELECT * FROM person WHERE id IN (SELECT personid FROM
     * personcourse WHERE courseid = " +courseid+ ")",
     * (rs, rowNum)->new PersonEntity(rs.getInt("id"), rs.getString("username"), rs.getString
     * ("firstname"), rs.getString("lastname"),
     * rs.getString("country"), rs.getString("email"), rs.getString("gender"),
     * rs.getString("accounttype"), rs.getString("companyname"), rs.getString
     * ("companylocation"),
     * rs.getString("companyservices"), rs.getString("role"), rs.getString("profileImage")));
     * return users;
     * }
     */

    //List<PersonEntity> getAllUnerolledUsers(Long courseid);

    //Returns users who are enrolled to this course
    //List<PersonEntity> getAllEnrolledUsers(Long courseid);

    //reports, users with courses
    //List<UserReport> getUserReports();
}
