package com.example.demo.model.domain.builder;


import com.example.demo.model.domain.User;

public final class UserBuilder {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    private int age;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(final String id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(final String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withAge(final int age) {
        this.age = age;
        return this;
    }

    public User build() {
        return new User(id, firstName, lastName, email, age);
    }
}
