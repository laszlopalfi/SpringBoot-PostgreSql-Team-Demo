package com.example.demo.model.json.builder;

import com.example.demo.model.json.User;

public final class UserBuilder {
    private String name;
    private String email;
    private long dob;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(final String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withDob(final long dob) {
        this.dob = dob;
        return this;
    }

    public User build() {
        return new User(name, email, dob);
    }
}
