package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.ProductEntity;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bezdj on 15/08/2017.
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Transactional
    @Override
    public void insertProduct(ProductEntity product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    //NOT USING IT! Keeping it to have the SQLQuery example.
    @Transactional
    @Override
    public void updateProduct(ProductEntity product) {
        String sql = "UPDATE products SET name = '"+ product.getName() +"'," +
                "SET product_description = '"+product.getProduct_description()+"'," +
                "SET create_date = '"+product.getCreateDate()+"'," +
                "SET price = "+product.getPrice()+"  WHERE id = " + product.getId();

        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
        //jdbcTemplate.query(sql, new ProductsMapper());
    }

    @Transactional
    @Override
    public ProductEntity getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = " + id;
        List<ProductEntity> products = jdbcTemplate.query(sql,
                (rs, rowNum)-> new ProductEntity(rs.getInt("id"), rs.getString("create_date"),
                        rs.getString("image"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("product_description"), rs.getString("currency")));
        return products.size() > 0 ? products.get(0) : new ProductEntity();
    }

    @Transactional
    @Override
    public List<ProductEntity> getAllProducts() {
        String sql = "SELECT * FROM products order by price";
        List<ProductEntity> products = jdbcTemplate.query(sql,
                (rs, rowNum)-> new ProductEntity(rs.getInt("id"), rs.getString("create_date"),
                        rs.getString("image"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("product_description"), rs.getString("currency")));
        return products.size() > 0 ? products : null;
    }

    @Transactional
    @Override
    public List<ProductEntity> getProductsByOrder(int id) {
        String sql = "SELECT * FROM products WHERE id IN (SELECT product_id FROM order_details WHERE order_id = "+ id +")";
        List<ProductEntity> products = jdbcTemplate.query(sql,
                (rs, rowNum)-> new ProductEntity(rs.getInt("id"), rs.getString("create_date"),
                        rs.getString("image"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("product_description"), rs.getString("currency")));
        return products.size() > 0 ? products : null;
    }

    @Transactional
    @Override
    public List<ProductEntity> getProductsByPerson(int id) {
        String sql = "SELECT * FROM products WHERE id IN (SELECT product_id FROM order_details WHERE person_id = "+ id +")";
        List<ProductEntity> products = jdbcTemplate.query(sql,
                (rs, rowNum)-> new ProductEntity(rs.getInt("id"), rs.getString("create_date"),
                        rs.getString("image"), rs.getString("name"), rs.getDouble("price"),
                        rs.getString("product_description"), rs.getString("currency")));
        return products.size() > 0 ? products : null;
    }
}
