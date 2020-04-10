package com.example.demo.service;

import com.example.demo.exception.UserRepositoryException;
import com.example.demo.factory.UserRepositoryFactory;
import com.example.demo.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepositoryFactory userRepositoryFactory;

    @Autowired
    public UserService(final UserRepositoryFactory userRepositoryFactory) {
        this.userRepositoryFactory = userRepositoryFactory;
    }

    public User getUser(final String id) throws UserRepositoryException {
        return userRepositoryFactory.getDefaultRepository().getUserById(id);
    }

    public List<User> getUsers() throws UserRepositoryException {
        return userRepositoryFactory.getDefaultRepository().getAllUsers();
    }

    public void addUser(final User user) throws UserRepositoryException {
        userRepositoryFactory.getDefaultRepository().addUser(user);
    }

    public void deleteUser(final String id) throws UserRepositoryException {
        userRepositoryFactory.getDefaultRepository().deleteUser(id);
    }
}
