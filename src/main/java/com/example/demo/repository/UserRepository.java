package com.example.demo.repository;

import com.example.demo.exception.UserRepositoryException;
import com.example.demo.model.domain.User;
import com.example.demo.repository.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcTemplate defaultJdbcTemplate;

    @Autowired
    public UserRepository(final NamedParameterJdbcTemplate jdbcTemplate, final JdbcTemplate defaultJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.defaultJdbcTemplate = defaultJdbcTemplate;
    }

    @Override
    public void addUser(final User user) throws UserRepositoryException {
        try {
            final SqlParameterSource queryParameters = new MapSqlParameterSource()
                    .addValue("id", UUID.fromString(user.getId()))
                    .addValue("firstName", user.getFirstName())
                    .addValue("lastName", user.getLastName())
                    .addValue("email", user.getEmail())
                    .addValue("age", user.getAge());

            final String sqlQuery = "INSERT INTO testdb.public.\"User\" (id, firstname, lastname, email, age)" +
                    "VALUES (:id,:firstName,:lastName, :email, :age)";

            jdbcTemplate.update(sqlQuery, queryParameters);
        } catch (final Exception e) {
            throw new UserRepositoryException(String.format("Failed to insert %s in the database", user.getFirstName()), e);
        }
    }

    @Override
    public User getUserById(final String id) throws UserRepositoryException {
        try {
            final SqlParameterSource queryParameters = new MapSqlParameterSource()
                    .addValue("id", UUID.fromString(id));

            final String sqlQuery = "SELECT * FROM testdb.public.\"User\" WHERE id = :id";

            return jdbcTemplate.queryForObject(
                    sqlQuery, queryParameters, new UserMapper());
        } catch (final Exception e) {
            throw new UserRepositoryException(String.format("Failed to retrieve user with id %s from the database", id), e);
        }
    }

    @Override
    public void deleteUser(final String id) throws UserRepositoryException {
        try {
            final SqlParameterSource queryParameters = new MapSqlParameterSource()
                    .addValue("id", UUID.fromString(id));

            final String sqlQuery = "DELETE FROM testdb.public.\"User\" WHERE id = :id";
            jdbcTemplate.update(sqlQuery, queryParameters);
        } catch (final Exception e) {
            throw new UserRepositoryException(String.format("Failed to retrieve user with id %s from the database", id), e);
        }
    }

    @Override
    public List<User> getAllUsers() throws UserRepositoryException {
        try {
            final String sqlQuery = "SELECT * FROM testdb.public.\"User\"";
            return jdbcTemplate.query(sqlQuery, new UserMapper());
        } catch (final Exception e) {
            throw new UserRepositoryException("Failed to retrieve all users from database", e);
        }
    }

    @Override
    public Integer countEntries() throws UserRepositoryException {
        try {
            final String sqlQuery = "SELECT COUNT(*) FROM testdb.public.\"User\"";
            return defaultJdbcTemplate.queryForObject(sqlQuery, Integer.class);
        } catch (final Exception e) {
            throw new UserRepositoryException("Failed to retrieve all users from database", e);
        }
    }
}
