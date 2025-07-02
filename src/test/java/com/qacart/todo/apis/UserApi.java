package com.qacart.todo.apis;


import com.qacart.todo.data.Route;
import com.qacart.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * This class provides methods to interact with the User API.
 * It includes methods for user registration and login.
 */

public class UserApi {

    // This method registers a new user in the API.
    public static Response register(User user) {

        return given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                // Serialize the request body to JSON format
                .body(user)
                .when()
                .post(Route.REGISTER_ROUTE)
                .then()
                .log().all()
                .extract().response();
    }

    // This method logs in an existing user in the API.
    public static Response login(User user) {

        return given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(Route.LOGIN_ROUTE)
                .then()
                .log().all()
                .extract().response();
    }
}