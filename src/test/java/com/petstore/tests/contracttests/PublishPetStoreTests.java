package com.petstore.tests.contracttests;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;

import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class PublishPetStoreTests {

    Map<String, String> headers = new HashMap<String, String>();

    // The rule is created to mock the service.
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("petstore", "localhost", 8090, this);

    @Pact (provider = "petstore", consumer = "traderdirectory")
    public RequestResponsePact createFragment(PactDslWithProvider builder) throws IOException{
        headers.put("Content-Type", "application/json");

        DslPart getPets = new PactDslJsonBody()
                .numberType("id")
                .stringType("name")
                .stringType("status")
                .object("category")
                    .numberType("id")
                    .stringType("name")
                .closeObject()
                .array("photoUrls")
                    .stringType()
                .closeArray()
                .array("tags")
                    .object()
                        .numberType("id")
                        .stringType("name")
                    .closeObject()
                .closeArray();

        return builder
                .given("Get Pet information")
                .uponReceiving("Get Pet call")
                    .path("/v2/pet/9216678377732861246")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .body(getPets)
                    .headers(headers)
                .toPact();
    }

    @Test
    @PactVerification ("petstore")
    public void runTest() {
        RestAssured.baseURI = "http://localhost:8090";

        Response response = RestAssured
                .given()
                .when()
                .get("/v2/pet/9216678377732861246");

        assert (response.getStatusCode() == 200);
    }
}