package com.springapp.mylms.repository;

import com.springapp.mylms.domain.PersonProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonProductRepository extends JpaRepository<PersonProductEntity, Long> {
    List<PersonProductEntity> findAllByPersonid(Long personId);
}
