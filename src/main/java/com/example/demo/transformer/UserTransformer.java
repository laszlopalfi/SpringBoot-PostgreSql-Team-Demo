package com.example.demo.transformer;

import com.example.demo.model.json.User;
import com.example.demo.model.json.builder.UserBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserTransformer {

    private static final Calendar CALENDAR = Calendar.getInstance();

    public List<User> toJson(final List<com.example.demo.model.domain.User> domainArray) {
        return domainArray.stream().map(this::toJson).collect(Collectors.toList());
    }

    public List<com.example.demo.model.domain.User> toDomain(final List<User> jsonArray) {
        return jsonArray.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public User toJson(final com.example.demo.model.domain.User domain) {
        return UserBuilder.anUser()
                .withName(getJsonName(domain.getFirstName(), domain.getLastName()))
                .withEmail(domain.getEmail())
                .withDob(getJsonDob(domain.getAge()))
                .build();
    }

    public com.example.demo.model.domain.User toDomain(final User json) {
        return com.example.demo.model.domain.builder.UserBuilder.anUser()
                .withId(UUID.randomUUID().toString())
                .withFirstName(getDomainFirstName(json.getName()))
                .withLastName(getDomainLastName(json.getName()))
                .withEmail(json.getEmail())
                .withAge(getDomainAge(json.getDob()))
                .build();
    }

    private String getDomainFirstName(final String name) {
        return name.split(" ")[0];
    }

    private String getDomainLastName(final String name) {
        return name.split(" ")[1];
    }

    private int getDomainAge(final long dob) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dob);
        return calendar.get(Calendar.YEAR);
    }

    private String getJsonName(final String firstName, final String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    private long getJsonDob(final int age) {
        CALENDAR.setTimeInMillis(Instant.now().toEpochMilli());
        return CALENDAR.get(Calendar.YEAR) - age;
    }
}