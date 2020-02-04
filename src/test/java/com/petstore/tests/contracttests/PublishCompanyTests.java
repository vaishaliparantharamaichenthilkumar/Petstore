package com.petstore.tests.contracttests;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;

import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class PublishCompanyTests {

    Map<String, String> headers = new HashMap<String, String>();

    // The rule is created to mock the service.
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("companies", "localhost", 8090, this);

    @Pact (provider = "companies", consumer = "traderdirectory")
    public RequestResponsePact createFragment(PactDslWithProvider builder) throws IOException{
        headers.put("Content-Type", "application/json");

        DslPart getCompanies = new PactDslJsonArray()
                .arrayEachLike()
                    .numberType("id")
                    .stringType("name")
                .closeObject();

        return builder
                .given("Get Companies information")
                .uponReceiving("Get Companies call")
                .path("/companies")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(getCompanies)
                .headers(headers)
                .toPact();
    }

    @Test
    @PactVerification ("companies")
    public void runTest() {
        RestAssured.baseURI = "http://localhost:8090";

        Response response = RestAssured
                .given()
                .when()
                .get("/companies");

        assert (response.getStatusCode() == 200);
    }
}