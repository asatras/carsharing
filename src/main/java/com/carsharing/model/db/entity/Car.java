package com.carsharing.model.db.entity;

import java.math.BigDecimal;

public class Car extends Entity {

    private static final long serialVersionUID = 583081053698455088L;
    private long id;
    private String brand;
    private BigDecimal price;
    private String description;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
