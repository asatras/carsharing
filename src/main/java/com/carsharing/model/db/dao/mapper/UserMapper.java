package com.carsharing.model.db.dao.mapper;

import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements EntityMapper<User> {

    @Override
    public User mapUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .passport(rs.getString("passport"))
                .role(Role.USER)
//                .role(Role.valueOf(rs.getString("role")))
//                .role(checkRole(rs))
                .balance(rs.getBigDecimal("balance"))
                .isActive(rs.getBoolean("isActive"))
                .build();
    }

    private Role checkRole(ResultSet rs) {
        try {
            if (rs.getString("role") == null) {
                return Role.USER;
            }
            return Role.valueOf(rs.getString("role"));
        } catch (SQLException e) {
            //TODO add logger
            e.printStackTrace();
        }  return null;
    }
}
