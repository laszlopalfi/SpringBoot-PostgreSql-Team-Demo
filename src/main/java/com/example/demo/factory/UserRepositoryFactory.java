package com.example.demo.factory;

import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryFactory {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryFactory(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public IUserRepository getDefaultRepository() {
        return userRepository;
    }
}
