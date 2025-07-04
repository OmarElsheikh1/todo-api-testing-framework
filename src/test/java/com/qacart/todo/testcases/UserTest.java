package com.qacart.todo.testcases;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * This class contains test cases for user registration and login functionalities.
 * It uses the UserApi to interact with the user-related endpoints and verifies
 * the expected behavior using assertions.
 */

@Feature("User Management Feature")
public class UserTest {

    @Story("User Registration Story")
    @Test(description = "Register a new user")
    public void shouldBeAbleToRegister() {

        // I've added the following comments to show how to create a User object in different ways

        // Create a User object and set its properties
        // Java object from User class
//        User user = new User("Omar", "Elsheikh", "omar6@example.com", "Test1234");

        // Using the fluent API style to set properties
//        User user = new User()
//                .setFirstName("Omar")
//                .setLastName("Elsheikh")
//                .setEmail("omar13@example.com")
//                .setPassword("Test1234");

        // Generate a random user using UserSteps
        User user = UserSteps.generateRandomUser();

        // Register the random user using the UserApi
        Response response = UserApi.register(user);

        // Deserialize the response body to a User object
        User returnedUser = response.body().as(User.class);
        // Assert that the returned user object has the expected values
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));
//        assertThat(response.path("firstName"), equalTo("Omar"));


        // Assert the response status code and body gaining the response from extracting it
        assertThat(response.statusCode(), equalTo(201));
    }

    @Story("User Registration Story")
    @Test(description = "Fail to register with an existing email")
    public void shouldNotBeAbleToRegisterWithTheSameEmail() {

//        // Using the fluent API style to set properties
//        User user = new User()
//                .setFirstName("Omar")
//                .setLastName("Elsheikh")
//                .setEmail("omar12@example.com")
//                .setPassword("Test1234");

        // Generate a random user using UserSteps
        User user = UserSteps.getRegisteredUser();

        // Register the random user using the UserApi
        Response response = UserApi.register(user);

        // Deserialize the response body to an ErrorMessages object
        Error returnedError = response.body().as(Error.class);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.EMAIL_IS_ALREADY_REGISTERED));
    }

    @Story("User Registration Story")
    @Test(description = "Login with valid credentials")
    public void shouldBeAbleToLogin() {

//        // Create a User object and set its properties
//        User user = new User("omar7@example.com", "Test1234");

//        // Using the fluent API style to set properties
//        User user = new User()
//                .setEmail("omar7@example.com")
//                .setPassword("Test1234");

        // Generate a random user using UserSteps
        User user = UserSteps.getRegisteredUser();

        // Create a User object for login with email and password
        User loginData = new User(user.getEmail(), user.getPassword());

        // Implement the test logic for user registration
        Response response = UserApi.login(loginData);

        // Deserialize the response body to an ErrorMessages object
        User returnedUser = response.body().as(User.class);

        // Assert that response contains expected values
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));
        assertThat(returnedUser.getAccessToken(), not(equalTo(user.getAccessToken())));


//                .assertThat().body("firstName", equalTo("Omar"))
//                .assertThat().body("access_token", not(equalTo(null)));
    }

    @Story("User Registration Story")
    @Test(description = "Fail to login with wrong password")
    public void shouldNotBeAbleToLoginIfThePasswordIsWrong() {

//        // Create a User object and set its properties
//        User user = new User("omar7@example.com", "Test1234");

//        // Using the fluent API style to set properties
//        User user = new User()
//                .setEmail("omar7@example.com")
//                .setPassword("Test1234Test");

        // Generate a random user using UserSteps
        User user = UserSteps.getRegisteredUser();
        // Create a User object for login with email and password
        User loginData = new User(user.getEmail(), "wrongPassword");

        // Implement the test logic for user registration
        Response response = UserApi.login(loginData);

        // Deserialize the response body to an ErrorMessages object
        Error returnedError = response.body().as(Error.class);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(401));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.EMAIL_OR_PASSWORD_IS_WRONG));

//                .assertThat().statusCode(401)
//                .assertThat().body("message", equalTo("The email and password combination is not correct, please fill a correct email and password"));
    }
}