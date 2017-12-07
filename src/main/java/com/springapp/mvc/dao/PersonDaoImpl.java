package com.springapp.mvc.dao;

import com.springapp.mvc.domain.Login;
import com.springapp.mvc.domain.PersonEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Override
    @Transactional
    public void insertPerson(PersonEntity person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    @Override
    public PersonEntity validateUser(Login login) {
        //selects * and puts the values into the object in mapper
        String sql = "SELECT * FROM person where username='" + login.getUsername() + "' and password='" + login.getPassword() + "'";
        List<PersonEntity> users = jdbcTemplate.query(sql,
                (rs, rowNum)-> new PersonEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("country"), rs.getString("email"), rs.getString("gender"),
                        rs.getString("accounttype"), rs.getString("companyname"), rs.getString("companylocation"),
                        rs.getString("companyservices"), rs.getString("role")));
        return users.size() > 0 ? users.get(0) : null;
    }

    //This is used for the AJAX call function.
    @Transactional
    @Override
    public PersonEntity getUserInfo(String username, String password){
        String sql = "SELECT * FROM person where username='" + username + "' and password='" + password + "'";
        List<PersonEntity> users = jdbcTemplate.query(sql,
                (rs, rowNum)-> new PersonEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("country"), rs.getString("email"), rs.getString("gender"),
                        rs.getString("accounttype"), rs.getString("companyname"), rs.getString("companylocation"),
                        rs.getString("companyservices"), rs.getString("role")));
        return users.size() > 0 ? users.get(0) : new PersonEntity();
    }

    @Transactional
    @Override
    public List<PersonEntity> getAllUsers() {
        List<PersonEntity> users = jdbcTemplate.query(
                "SELECT * FROM person",
                (rs, rowNum)-> new PersonEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("country"), rs.getString("email"), rs.getString("gender"),
                        rs.getString("accounttype"), rs.getString("companyname"), rs.getString("companylocation"),
                        rs.getString("companyservices"), rs.getString("role"))
        );
        return users;
    }

    @Transactional
    @Override
    public List<PersonEntity> getAllUnerolledUsers(int courseid) {
        List<PersonEntity> users = jdbcTemplate.query("SELECT * FROM person WHERE id NOT IN (SELECT personid FROM personcourse WHERE courseid = " +courseid+ ")",
                (rs, rowNum)->new PersonEntity(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("country"), rs.getString("email"), rs.getString("gender"),
                        rs.getString("accounttype"), rs.getString("companyname"), rs.getString("companylocation"),
                        rs.getString("companyservices"), rs.getString("role")));
        return users;
    }

    @Transactional
    @Override
    public PersonEntity getUserById(int userid) {
        String sql1 = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql1, new Object[]{userid}, new BeanPropertyRowMapper<>(PersonEntity.class));
        //return user.size() > 0 ? user.get(0) : new PersonEntity();
    }
}