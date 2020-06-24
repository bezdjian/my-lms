package com.springapp.mylms.service;

import com.springapp.mylms.repository.PersonProductRepository;
import com.springapp.mylms.entity.PersonProductEntity;
import com.springapp.mylms.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class PersonProductService {

    @Autowired
    private PersonProductRepository personProductRepository;

    public void insertPersonProduct(PersonProductEntity personProductEntity) {
        personProductRepository.save(personProductEntity);
    }

    public ProductEntity getPersonProductById(Long productId) {
        // return personProductRepository.findById(productId).get();
        return null;
    }

    public List<ProductEntity> getAllPersonProducts(int personId) {
        // return personProductRepository.getAllPersonProducts(personId);
        return Collections.emptyList();
    }
}
