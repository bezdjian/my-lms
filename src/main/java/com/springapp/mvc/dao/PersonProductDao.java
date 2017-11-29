package com.springapp.mvc.dao;

import com.springapp.mvc.domain.PersonProductEntity;
import com.springapp.mvc.domain.ProductEntity;

import java.util.List;

public interface PersonProductDao {
    void insertPersonProduct(PersonProductEntity personProductEntity);
    ProductEntity getPersonProductById(int productId);
    List<ProductEntity> getAllPersonProducts(int personId);
}
