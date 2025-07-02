package com.qacart.todo.apis;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * This class provides methods to interact with the Todo API.
 * It includes methods for adding, retrieving, updating, and deleting todo items.
 */

public class TodoApi {

    // This method adds a new todo item to the API.
    public static Response addTodo(Todo todo, String token) {

        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when()
                .post(Route.TODOS_ROUTE)
                .then()
                .log().all()
                .extract().response();
    }

    // This method retrieves all todo items from the API.
    public static Response getAllTodos(String token) {

        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(Route.TODOS_ROUTE)
                .then()
                .log().all()
                .extract().response();
    }

    // This method retrieves a specific todo item by its ID from the API.
    public static Response getTodo(String token, String taskId) {

        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(Route.TODOS_ROUTE + "/" + taskId)
                .then()
                .log().all()
                .extract().response();
    }

    // This method updates an existing todo item in the API.
    public static Response updateTodo(Todo todo, String token, String taskId) {

        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when()
                .put(Route.TODOS_ROUTE + "/" + taskId)
                .then()
                .log().all()
                .extract().response();
    }

    // This method deletes a specific todo item by its ID from the API.
    public static Response deleteTodo(String token, String taskId) {

        return given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .when()
                .delete(Route.TODOS_ROUTE + "/" + taskId)
                .then()
                .log().all()
                .extract().response();
    }
}