package com.carsharing.model.db.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);

    void deleteEntity(T entity);

    void close();
}
