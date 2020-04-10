package com.example.demo.model.domain;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public User(final String id, final String firstName, final String lastName, final String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}
