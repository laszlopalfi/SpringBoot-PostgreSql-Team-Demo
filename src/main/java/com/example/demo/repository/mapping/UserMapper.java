package com.example.demo.repository.mapping;

import com.example.demo.model.domain.User;
import com.example.demo.model.domain.builder.UserBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(final ResultSet resultSet, final int i) throws SQLException {
        return UserBuilder.anUser()
                .withId(resultSet.getString("id"))
                .withFirstName(resultSet.getString("firstname"))
                .withLastName(resultSet.getString("lastname"))
                .withEmail(resultSet.getString("email"))
                .withAge(resultSet.getInt("age"))
                .build();
    }
}
