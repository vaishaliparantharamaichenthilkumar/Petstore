package com.petstore.tests.testlibs;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class HttpUtils {

    //Function for POST Method
   public static Response postHttpRequest(String baseUrl, String path, JsonObject jsonBody){
       RestAssured.baseURI = baseUrl;
       return RestAssured
               .given()
               .contentType("application/json")
               .body(jsonBody)
               .when()
               .post(path);
   }

   //Function for GET Method
   public static Response getHttpRequest(String baseUrl, String path){
       RestAssured.baseURI = baseUrl;
       return RestAssured
               .given()
               .when()
               .get(path);
   }

   //Function for PUT Method
   public static Response putHttpRequest(String baseUrl, String path, JsonObject jsonBody){
       RestAssured.baseURI = baseUrl;
       return RestAssured
               .given()
               .contentType("application/json")
               .body(jsonBody)
               .when()
               .put(path);
   }

   //Function for DELETE Method
    public static Response deleteHttpRequest(String baseUrl, String path){
       RestAssured.baseURI = baseUrl;
       return RestAssured
               .given()
               .when()
               .delete(path);
    }

    //Function for POST Method to publish the test results in the PractiTest application url
    public static Response postPractiTestHttpRequest(String baseUrl, String path, String apiToken, String userEmailId, JSONObject jsonBody){
        RestAssured.baseURI = baseUrl;
        return RestAssured
                .given()
                .contentType("application/json")
                .queryParam("api_token", apiToken)
                .queryParam("developer_email", userEmailId)
                .body(jsonBody)
                .when()
                .post(path);
    }
}
