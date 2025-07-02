package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This POJO (Plain Old Java Object) class represents a User model, which is used to map the JSON structure
 * */

// This annotation ensures that null fields are not included in the JSON output
// It means don't include any field in the JSON output if it is null.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    // Fields for User model, must match the JSON structure expected by the API
    // So that Jackson can map the JSON to this class
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    // Field for access token, annotated with @JsonProperty to match the JSON key (access_token)
    @JsonProperty("access_token")
    private String accessToken;
    private String userID;


    public User() {
        // Default constructor for Jackson to create an instance of User
    }

    // Constructor to initialize User object with all fields
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Constructor to initialize User object with email and password only
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}