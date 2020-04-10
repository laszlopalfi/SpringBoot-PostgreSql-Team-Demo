package com.example.demo.repository;

import com.example.demo.exception.UserRepositoryException;
import com.example.demo.model.domain.User;

import java.util.List;

public interface IUserRepository {

    void addUser(final User user) throws UserRepositoryException;

    void deleteUser(final String id) throws UserRepositoryException;

    List<User> getAllUsers() throws UserRepositoryException;

    User getUserById(final String id) throws UserRepositoryException;

    Integer countEntries() throws UserRepositoryException;
}
