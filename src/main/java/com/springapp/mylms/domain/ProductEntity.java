package com.springapp.mylms.domain;

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
 * Created by bezdj on 15/08/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products", schema = "mylms")
public class ProductEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "image")
    private String image;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "currency")
    private String currency;
    @Column(name = "product_description")
    private String productDescription;
}
