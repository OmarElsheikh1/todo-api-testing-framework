package com.qacart.todo.testcases;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.Todo;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Feature("Todo Management Feature")
public class TodoTest {

    @Story("Todo Management Story")
    @Test(description = "Add a new todo item")
    public void shouldBeAbleToAddTodo() {

//        // Create a Todo object and set its properties
//        Todo todo = new Todo(false, "Learn Appium");
//        // Using the fluent API style to set properties
//        Todo todo = new Todo()
//                .setCompleted(false)
//                .setItem("Learn Appium");


        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Generate a random todo using TodoSteps
        Todo todo = TodoSteps.generateRandomTodo();

        // Call the API to add a todo item, passing the token for authentication using POST method
        Response response = TodoApi.addTodo(todo, token);

        // Deserialize the response body to a Todo object, by parsing the response body [response.body()]
        Todo returnedTodo = response.body().as(Todo.class);


        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(), equalTo(todo.getIsCompleted()));
    }

    @Story("Todo Management Story")
    @Test(description = "Fail to add todo if isCompleted is missing")
    public void shouldNotBeAbleToAddTodoIfIsCompletedIsMissing() {

//        // Define the request body for adding a todo item, in JSON format
//        Todo todo = new Todo("Learn Appium");

        // Using the fluent API style to set properties
        Todo todo = new Todo()
                .setItem("Learn Appium");

        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Call the API to add a todo item
        Response response = TodoApi.addTodo(todo, token);

        // Deserialize the response body to an ErrorMessages object
        Error returnedError = response.body().as(Error.class);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED));
    }

    @Story("Todo Management Story")
    @Test(description = "Get all todo items")
    public void shouldBeAbleToGetAllTodos() {

        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Generate a random todo using TodoSteps
        Todo todo = TodoSteps.generateRandomTodo();
        // Call the API to add a todo item, passing the token for authentication
        TodoApi.addTodo(todo, token);

        // Call the API to get all todo items
        Response response = TodoApi.getAllTodos(token);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("tasks.item"), hasItems(todo.getItem()));
    }

    @Story("Todo Management Story")
    @Test(description = "Get a todo item by ID")
    public void shouldBeAbleToGetATodoByID() {

        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Generate a random todo using TodoSteps
        Todo todo = TodoSteps.generateRandomTodo();

        // Task ID to retrieve
        String todoId = TodoSteps.getTodoId(todo, token);

        // Call the API to get a todo item by ID & pass the token for authentication
        Response response = TodoApi.getTodo(token, todoId);

        // Deserialize the response body to a Todo object
        Todo returnedTodo = response.body().as(Todo.class);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(), equalTo(false));
    }

    @Story("Todo Management Story")
    @Test(description = "Update a todo item")
    public void shouldBeAbleToUpdateATodo() {

//        // Define the request body for updating a todo item, in JSON format
//        // Create a Todo object and set its properties
//        Todo todo = new Todo(false, "Learn Appium");
//        // Using the fluent API style to set properties
//        Todo todo = new Todo()
//                .setCompleted(false)
//                .setItem("Learn Cucumber");

        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Generate a random todo using TodoSteps
        Todo todo = TodoSteps.generateRandomTodo();

        // Task ID to retrieve
        String todoId = TodoSteps.getTodoId(todo, token);

        // Call the API to update a todo item by ID
        Response response = TodoApi.updateTodo(todo, token, todoId);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("item"), equalTo(todo.getItem()));
        assertThat(response.path("isCompleted"), equalTo(false));
    }

    @Story("Todo Management Story")
    @Test(description = "Delete a todo item")
    public void shouldBeAbleToDeleteATodo() {

        // Bearer token for authentication, it generates a random user and returns the token
        String token = UserSteps.getUserToken();

        // Generate a random todo using TodoSteps
        Todo todo = TodoSteps.generateRandomTodo();

        // Task ID to retrieve
        String todoId = TodoSteps.getTodoId(todo, token);

        // Call the API to delete a todo item by ID
        Response response = TodoApi.deleteTodo(token, todoId);

        // Deserialize the response body to a Todo object
        Todo returnedTodo = response.body().as(Todo.class);

        // Assert the response status code and body
        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(), equalTo(false));
    }
}