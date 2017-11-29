package com.springapp.mvc.dao;

import com.springapp.mvc.domain.PersonProductEntity;
import com.springapp.mvc.domain.ProductEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

public class PersonProductDaoImpl implements PersonProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Transactional
    @Override
    public void insertPersonProduct(PersonProductEntity personProductEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(personProductEntity);
    }

    @Override
    public ProductEntity getPersonProductById(int id) {
        return null;
    }

    @Override
    public List<ProductEntity> getAllPersonProducts(int personid) {
        String sql = "SELECT * FROM products WHERE id IN (SELECT productid FROM personproducts WHERE personid = " + personid +" )";
        List<ProductEntity> ppe = jdbcTemplate.query(sql,
                (rs, rowNum)->new ProductEntity(rs.getInt("id"), rs.getString("create_date"),
                        rs.getString("image"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("product_description"), rs.getString("currency")));
        return ppe.size() > 0 ? ppe :  null;
    }
}
