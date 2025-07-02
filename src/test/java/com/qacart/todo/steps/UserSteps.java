package com.qacart.todo.steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

/*
    * This class contains methods for generating random user data and registering users.
 */

public class UserSteps {

    // Generate a random user, by using java faker library
    public static User generateRandomUser() {

        // Create a Faker instance to generate random data
        Faker faker = new Faker();
        String firsName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "iLikeQAcart";

        // Create and return a new User object with the generated data
        return new User(firsName, lastName, email, password);
    }

    // This method generates a random user and registers it using the UserApi
    public static User getRegisteredUser() {

        // Generate a random user and register it using the UserApi
        User user = generateRandomUser();
        UserApi.register(user);
        return user;
    }

    // This method generates a random user and returns the access token after registration
    public static String getUserToken() {

        User user = generateRandomUser();
        Response response = UserApi.register(user);
        return response.body().path("access_token");
    }
}
