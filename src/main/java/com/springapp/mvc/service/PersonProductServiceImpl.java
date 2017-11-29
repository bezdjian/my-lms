package com.springapp.mvc.service;

import com.springapp.mvc.dao.PersonProductDao;
import com.springapp.mvc.domain.PersonProductEntity;
import com.springapp.mvc.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonProductServiceImpl implements PersonProductService {

    @Autowired
    PersonProductDao personProductDao;

    @Override
    public void insertPersonProduct(PersonProductEntity personProductEntity) {
        personProductDao.insertPersonProduct(personProductEntity);
    }

    @Override
    public ProductEntity getPersonProductById(int productId) {
        return personProductDao.getPersonProductById(productId);
    }

    @Override
    public List<ProductEntity> getAllPersonProducts(int personId) {
        return personProductDao.getAllPersonProducts(personId);
    }
}
