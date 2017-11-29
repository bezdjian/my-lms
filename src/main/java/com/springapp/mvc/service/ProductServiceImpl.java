package com.springapp.mvc.service;

import com.springapp.mvc.dao.PersonDao;
import com.springapp.mvc.dao.ProductDao;
import com.springapp.mvc.domain.Login;
import com.springapp.mvc.domain.PersonEntity;
import com.springapp.mvc.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void insertProduct(ProductEntity product) {
        productDao.insertProduct(product);
    }

    @Override
    public void updateProduct(ProductEntity product) {
        productDao.updateProduct(product);
    }

    @Override
    public ProductEntity getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public List<ProductEntity> getProductsByOrder(int id) {
        return productDao.getProductsByOrder(id);
    }

    @Override
    public List<ProductEntity> getProductsByPerson(int id) {
        return productDao.getProductsByPerson(id);
    }
}
