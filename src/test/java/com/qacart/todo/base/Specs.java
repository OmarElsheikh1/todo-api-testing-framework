package com.qacart.todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/*
    * This class provides methods to set up the base specifications for API requests.
 */

public class Specs {

    // This method retrieves the base URL for the API based on the environment variable.
    public static String getEnv() {

        String env = System.getProperty("env", "PRODUCTION");
        String baseUrl = "https://todo.qacart.com";
        switch (env) {
            case "PRODUCTION":
                baseUrl = "https://todo.qacart.com";
                break;
            case "LOCAL":
                baseUrl = "http://localhost:8080";
                break;
            default:
                throw new RuntimeException("Environment is not supported: ");
        }
        return baseUrl;
    }

    // This method provides a RequestSpecification for the Todo API.
    public static RequestSpecification getRequestSpec() {

        return given()
//                .baseUri("https://todo.qacart.com")
                .baseUri(getEnv())
                .contentType(ContentType.JSON)
                .log().all();
    }
}