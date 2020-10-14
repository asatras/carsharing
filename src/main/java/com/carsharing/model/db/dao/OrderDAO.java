package com.carsharing.model.db.dao;

import com.carsharing.model.db.entity.Order;

public interface OrderDAO {
    Order findById(long id);
}
