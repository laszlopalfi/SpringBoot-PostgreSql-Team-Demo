package com.example.demo.model.json;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty")
    @Length(max = 20, message = "Name should be at max 10 characters")
    private String name;

    @NotNull(message = "Email cannot be null!")
    @Email
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    private long dob;

    public User() {
    }

    public User(final String name, final String email, final long dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public long getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }
}
