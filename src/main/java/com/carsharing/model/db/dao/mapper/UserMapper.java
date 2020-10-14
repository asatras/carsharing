package com.carsharing.model.db.dao.mapper;

import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements EntityMapper<User> {

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .passport(rs.getString("passport"))
//                .role(Role.valueOf(rs.getString("role")))
//                .role(Role.values()[rs.getInt("roles_id") - 1])
                .balance(rs.getBigDecimal("balance"))
                .isActive(rs.getBoolean("isActive"))
                .build();
    }
}
