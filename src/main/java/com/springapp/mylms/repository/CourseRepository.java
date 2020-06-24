package com.springapp.mylms.repository;

import com.springapp.mylms.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bezdj on 04/02/2017.
 */
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}