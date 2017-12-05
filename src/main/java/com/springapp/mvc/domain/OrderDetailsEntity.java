package com.springapp.mvc.domain;

import javax.persistence.*;

/**
 * Created by bezdj on 17/08/2017.
 */
@Entity
@Table(name = "order_details", schema = "mylms", catalog = "")
public class OrderDetailsEntity {
    private int id;
    private int amount;
    private double price;
    private int quantity;
    private int order_id;
    private int product_id;
    private int person_id;
    private String orderDate;
    private String orderNumber;

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
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Basic
    @Column(name = "product_id")
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Basic
    @Column(name = "person_id")
    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }


    @Basic
    @Column(name = "order_date")
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    @Basic
    @Column(name = "order_number")
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    //Constructor
    public OrderDetailsEntity(){}

    public OrderDetailsEntity(int id, int amount, double price, int quantity, int order_id, int product_id, int person_id, String orderDate, String orderNumber) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.quantity = quantity;
        this.order_id = order_id;
        this.product_id = product_id;
        this.person_id = person_id;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntity that = (OrderDetailsEntity) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (quantity != that.quantity) return false;
        if (order_id != that.order_id) return false;
        if (product_id != that.product_id) return false;
        return person_id == that.person_id;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + amount;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + order_id;
        result = 31 * result + product_id;
        result = 31 * result + person_id;
        return result;
    }
}
