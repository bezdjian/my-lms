package com.springapp.mylms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bezdj on 17/08/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details", schema = "mylms")
public class OrderDetailsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "order_id")
    private Long order_id;
    @Column(name = "product_id")
    private Long product_id;
    @Column(name = "person_id")
    private Long person_id;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "order_number")
    private String orderNumber;
}
