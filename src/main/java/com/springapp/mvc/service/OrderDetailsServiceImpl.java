package com.springapp.mvc.service;

import com.springapp.mvc.dao.OrderDetailsDao;
import com.springapp.mvc.domain.OrderDetailsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsDao orderDetailsDao;

    @Override
    public void insertOrderDetail(OrderDetailsEntity order) {
        orderDetailsDao.insertOrderDetail(order);
    }

    @Override
    public OrderDetailsEntity getOrderDetailById(int id) {
        return orderDetailsDao.getOrderDetailById(id);
    }

    @Override
    public List<OrderDetailsEntity> getAllOrderDetails() {
        return orderDetailsDao.getAllOrderDetails();
    }

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByProduct(int id) {
        return orderDetailsDao.getOrderDetailsByProduct(id);
    }

    @Override
    public List<OrderDetailsEntity> getOrderDetailsByPerson(int id) {
        return orderDetailsDao.getOrderDetailsByPerson(id);
    }
}
