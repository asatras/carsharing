package com.carsharing.model.db.entity;

public class CarClass extends Entity {

    private static final long serialVersionUID = 4496388395701316387L;
    private long id;
    private String carClass;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }
}
