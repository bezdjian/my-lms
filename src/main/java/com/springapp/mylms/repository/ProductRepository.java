package com.springapp.mylms.repository;

import com.springapp.mylms.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bezdj on 17/08/2017.
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
