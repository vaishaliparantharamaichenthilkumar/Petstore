package com.petstore.tests.testlibs;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

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
}
