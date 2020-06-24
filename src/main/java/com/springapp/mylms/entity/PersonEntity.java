package com.springapp.mylms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by bezdj on 02/02/2017.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person", schema = "mylms")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "role")
    private String role;

    @Transient
    private String profileImage;
    @Transient
    private String fullName;
    //Company
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_location")
    private String companyLocation;
    @Column(name = "company_services")
    private String companyServices;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<CourseEntity> courses;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<EmploymentEntity> employment;
}
