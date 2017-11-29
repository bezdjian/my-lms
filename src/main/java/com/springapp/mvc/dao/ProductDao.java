package com.springapp.mvc.dao;

import com.springapp.mvc.domain.CourseEntity;
import com.springapp.mvc.domain.ProductEntity;

import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */
public interface ProductDao {
    void insertProduct(ProductEntity product);
    void updateProduct(ProductEntity product); //the one above should have worked!
    ProductEntity getProductById(int id);
    List<ProductEntity> getAllProducts();
    List<ProductEntity> getProductsByOrder(int id);
    List<ProductEntity> getProductsByPerson(int id);
}
