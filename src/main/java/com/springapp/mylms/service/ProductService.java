package com.springapp.mylms.service;

import com.springapp.mylms.repository.ProductRepository;
import com.springapp.mylms.domain.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductEntity> getProductsByOrder(int id) {
        //return productRepository.findProductsByOrder(id);
        return Collections.emptyList();
    }

    public List<ProductEntity> getProductsByPerson(int id) {
        // return productRepository.getProductsByPerson(id);
        return Collections.emptyList();
    }
}
