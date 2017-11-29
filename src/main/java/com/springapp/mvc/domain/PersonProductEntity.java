package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by bezdj on 14/11/2017.
 */
@Entity
@Table(name = "personproducts", schema = "registration", catalog = "")
public class PersonProductEntity {
    private int id;
    private int personid;
    private int productid;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonProductEntity(){}

    public PersonProductEntity(int id, int personid, int productid) {
        this.id = id;
        this.personid = personid;
        this.productid = productid;
    }

    public int getPersonid() {

        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public int getProductid() {
        return productid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonProductEntity that = (PersonProductEntity) o;

        if (id != that.id) return false;
        if (personid != that.personid) return false;
        return productid == that.productid;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personid;
        result = 31 * result + productid;
        return result;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
}
