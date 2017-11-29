package com.springapp.mvc.dao;

import com.springapp.mvc.domain.OrderDetailsEntity;

import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */
public interface OrderDetailsDao {
    void insertOrderDetail(OrderDetailsEntity order);
    OrderDetailsEntity getOrderDetailById(int id);
    List<OrderDetailsEntity> getAllOrderDetails();
    List<OrderDetailsEntity> getOrderDetailsByProduct(int id);
    List<OrderDetailsEntity> getOrderDetailsByPerson(int id);
}
