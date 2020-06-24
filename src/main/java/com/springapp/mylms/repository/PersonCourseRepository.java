package com.springapp.mylms.repository;

import com.springapp.mylms.entity.PersonCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bezdj on 04/02/2017.
 */

@Repository
public interface PersonCourseRepository extends JpaRepository<PersonCourseEntity, Long> {
    List<PersonCourseEntity> findPersonCourseEntitiesByPersonId(Long id);
}
