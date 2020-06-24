package com.springapp.mylms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bezdj on 15/08/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course", schema = "mylms")
public class CourseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "coursename")
    private String coursename;
    @Column(name = "description")
    private String description;
    @Column(name = "idnumber")
    private String idnumber;
    @Column(name = "courseimage")
    private String courseimage;
    @Column(name = "categoryid")
    private int categoryid;
}
