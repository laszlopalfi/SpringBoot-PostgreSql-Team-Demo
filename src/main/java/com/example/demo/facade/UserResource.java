package com.example.demo.facade;

import com.example.demo.exception.UserRepositoryException;
import com.example.demo.model.json.User;
import com.example.demo.service.UserService;
import com.example.demo.transformer.UserTransformer;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    private UserTransformer userTransformer;
    private UserService userService;

    @Autowired
    public UserResource(final UserService userService, final UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }

    @GetMapping
    @Timed(histogram = true)
    public List<User> getUsers() throws UserRepositoryException {
        return userTransformer.toJson(userService.getUsers());
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Timed(histogram = true)
    public User getUser(@PathVariable final String userId) throws UserRepositoryException {
        return userTransformer.toJson(userService.getUser(userId));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Timed(histogram = true)
    public void deleteUser(@PathVariable final String userId) throws UserRepositoryException {
        userService.deleteUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Timed(histogram = true)
    public void addUser(@Valid @RequestBody final User user) throws UserRepositoryException {
        userService.addUser(userTransformer.toDomain(user));
    }
}
