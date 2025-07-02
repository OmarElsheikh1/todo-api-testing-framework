package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * This POJO (Plain Old Java Object) class represents a Todo model, which is used to map the JSON structure
 * */

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON output
public class Todo {

//    @JsonInclude(JsonInclude.Include.NON_DEFAULT) // Exclude default values from JSON output
//    We will use Boolean instead of boolean to allow null values
    // Fields for Todo model, must match the JSON structure expected by the API
    @JsonProperty("isCompleted")
    private Boolean isCompleted;
    @JsonProperty("_id")
    private String id;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;


    public Todo() {
        // Default constructor for Jackson to create an instance of Todo
    }

    // Constructor to initialize Todo object with item only
    public Todo(String item) {
        this.item = item;
    }

    // Constructor to initialize Todo object with all fields
    public Todo(Boolean isCompleted, String item) {
        this.item = item;
        this.isCompleted = isCompleted;
    }


    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public Todo setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public Todo setId(String id) {
        this.id = id;
        return this;
    }

    public String getItem() {
        return item;
    }

    public Todo setItem(String item) {
        this.item = item;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public Todo setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Todo setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("__v")
    public String getV() {
        return v;
    }

    @JsonProperty("__v")
    public Todo setV(String v) {
        this.v = v;
        return this;
    }
}