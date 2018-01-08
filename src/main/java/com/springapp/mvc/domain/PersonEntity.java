package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by bezdj on 02/02/2017.
 */
@Entity
@Table(name = "person", schema = "mylms", catalog = "")
public class PersonEntity {
    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String email;
    private String gender;
    private String accounttype;
    private String role;
    //Company
    private String companyname;
    private String companylocation;
    private String companyservices;

    private String fullName;
    private String profileImage;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Basic
    @Column(name = "accounttype")
    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    @Basic
    @Column(name = "companyname")
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }


    @Basic
    @Column(name = "companylocation")
    public String getCompanylocation() {
        return companylocation;
    }

    public void setCompanylocation(String companylocation) {
        this.companylocation = companylocation;
    }

    @Basic
    @Column(name = "companyservices")
    public String getCompanyservices() {
        return companyservices;
    }

    public void setCompanyservices(String companyservices) {
        this.companyservices = companyservices;
    }


    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName(){
        return this.firstname + " " + this.lastname;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //Constructor
    public PersonEntity(){}
    public PersonEntity(int id,String username, String firstname, String lastname, String country, String email, String gender, String accounttype,
                        String companyname, String companylocation, String companyservices, String role, String profileImage) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.email = email;
        this.gender = gender;
        this.accounttype = accounttype;
        this.companyname= companyname;
        this.companylocation = companylocation;
        this.companyservices = companyservices;
        this.role = role;
        this.profileImage = profileImage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (companyname != null ? !companyname.equals(that.companyname) : that.companyname != null) return false;
        if (companylocation != null ? !companylocation.equals(that.companylocation) : that.companylocation != null) return false;
        if (companyservices != null ? !companyservices.equals(that.companyservices) : that.companyservices != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (companylocation != null ? companylocation.hashCode() : 0);
        result = 31 * result + (companyservices != null ? companyservices.hashCode() : 0);
        return result;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
