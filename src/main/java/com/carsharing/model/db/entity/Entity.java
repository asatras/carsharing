package com.carsharing.model.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 5383732494901494724L;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
