package com.springapp.mvc.dao;

import com.springapp.mvc.domain.OrderDetailsEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bezdj on 15/08/2017.
 */

@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Override
    public void insertOrderDetail(OrderDetailsEntity order) {
        sessionFactory.getCurrentSession().saveOrUpdate(order);
    }

    @Override
    public OrderDetailsEntity getOrderDetailById(int id) {
        String sql = "SELECT * FROM order_details WHERE id = " + id;
        List<OrderDetailsEntity> products = jdbcTemplate.query(sql, new OrderDetailsMapper());
        return products.size() > 0 ? products.get(0) : new OrderDetailsEntity();
    }

    @Override
    public List<OrderDetailsEntity> getAllOrderDetails() {
        String sql = "SELECT * FROM order_details";
        List<OrderDetailsEntity> products = jdbcTemplate.query(sql, new OrderDetailsMapper());
        return products.size() > 0 ? products : null;
    }

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByProduct(int id) {
        String sql = "SELECT * FROM order_details WHERE product_id = " + id;
        List<OrderDetailsEntity> products = jdbcTemplate.query(sql, new OrderDetailsMapper());
        return products.size() > 0 ? products : null;
    }

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByPerson(int id) {
        String sql = "SELECT * FROM order_details WHERE person_id = " + id;
        List<OrderDetailsEntity> products = jdbcTemplate.query(sql, new OrderDetailsMapper());
        return products.size() > 0 ? products : null;
    }
}



class OrderDetailsMapper implements RowMapper<OrderDetailsEntity> {

    public OrderDetailsEntity mapRow(ResultSet rs, int arg1) throws SQLException {

        OrderDetailsEntity product = new OrderDetailsEntity();

        product.setId(rs.getInt("id"));
        product.setAmount(rs.getInt("amount"));
        product.setPrice(rs.getDouble("price"));
        product.setQuantity(rs.getInt("quantity"));
        product.setOrder_id(rs.getInt("order_id"));
        product.setProduct_id(rs.getInt("product_id"));
        product.setPerson_id(rs.getInt("person_id"));
        product.setOrderDate(rs.getString("order_date"));
        product.setOrderNumber(rs.getString("order_number"));
        return product;
    }
}



