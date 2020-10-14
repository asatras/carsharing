package com.carsharing.model.db.entity;

public class Status extends Entity {

    private static final long serialVersionUID = 2602204687645469491L;
    private long id;
    private String status;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
