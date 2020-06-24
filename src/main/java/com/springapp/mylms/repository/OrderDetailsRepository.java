package com.springapp.mylms.repository;

import com.springapp.mylms.domain.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bezdj on 17/08/2017.
 */

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {
    //List<OrderDetailsEntity> findOrderDetailsEntitiesByProduct_id(Long id);
    //List<OrderDetailsEntity> findOrderDetailsEntitiesByPerson_id(Long id);
}
