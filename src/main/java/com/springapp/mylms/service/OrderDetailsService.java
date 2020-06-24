package com.springapp.mylms.service;

import com.springapp.mylms.repository.OrderDetailsRepository;
import com.springapp.mylms.domain.OrderDetailsEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(
            OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public void insertOrderDetail(OrderDetailsEntity order) {
        orderDetailsRepository.save(order);
    }

    public OrderDetailsEntity getOrderDetailById(Long id) {
        return orderDetailsRepository.findById(id).get();
    }

    public List<OrderDetailsEntity> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public List<OrderDetailsEntity> getOrderDetailsByProduct(Long id) {
        //return orderDetailsRepository.findOrderDetailsEntitiesByProduct_id(id);
        return Collections.emptyList();
    }

    public List<OrderDetailsEntity> getOrderDetailsByPerson(Long id) {
        // return orderDetailsRepository.findOrderDetailsEntitiesByPerson_id(id);
        return Collections.emptyList();
    }
}
