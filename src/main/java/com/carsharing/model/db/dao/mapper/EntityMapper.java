package com.carsharing.model.db.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Defines general contract for mapping database result set rows to application entities.
 * Implementations are not supposed to move cursor of the resultSet via next() method,
 * but only extract information from the row in current cursor position.
 */
public interface EntityMapper<T> {
    T mapUser(ResultSet rs) throws SQLException;
}
