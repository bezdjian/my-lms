package com.springapp.mylms.repository;

import com.springapp.mylms.domain.CourseEntity;
import com.springapp.mylms.domain.PersonCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bezdj on 04/02/2017.
 */

@Repository
public interface PersonCourseRepository extends JpaRepository<PersonCourseEntity, Long> {
    CourseEntity findPersonCourseEntitiesByPersonid(Long id);
}
