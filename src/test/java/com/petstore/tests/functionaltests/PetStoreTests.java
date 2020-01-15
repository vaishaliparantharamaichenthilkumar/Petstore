package com.petstore.tests.functionaltests;

import com.google.gson.JsonObject;
import com.petstore.tests.testlibs.*;

import io.restassured.response.Response;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PetStoreTests {

    JSONParser parser = new JSONParser();
    Validation validation = new Validation();
    TestUtils testUtils = new TestUtils();

    public static String petIdCreated;
    public static String practiTestFileName = Config.pt_fileName;
    public static String projectId = Config.pt_ProjectId;
    public static String instanceId;
    int status;

    //Pre-Requisite -> Get the host information based on the environment.
    @Before
    public void init(){
        Config.getHostName();
    }

    //TestCase-01 To create a Pet for PetStore and validate the Pet details are created correctly.
    @Test
    public void tc01createPetTests() throws Exception, AssertionError {
        instanceId = Config.pt_testCase_001;
        String baseUrl = Config.hostname;
        String path = Config.petApi;
        String fileName = Config.createFileName;
        String petName = TestData.petName;
        JsonObject jsonString = testUtils.readJsonPrepareData(fileName);
        Response response = HttpUtils.postHttpRequest(baseUrl, path, jsonString);

        //Verify Pet is created successfully.
        assert (response.getStatusCode() == 200);
        assert (response.getBody().asString().length() > 0);

        //Verify whether the Pet created matches with the input given.
        JSONObject obj = (JSONObject) parser.parse(response.getBody().asString());
        petIdCreated = obj.get("id").toString();
        try{
            validation.validatePetDetails(obj,petName);
            //Update PractiTest
            status=0;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
        catch(AssertionError e){
            //Update PractiTest
            status=1;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
    }

    //TestCase-02 To update a Pet for PetStore and validate the Pet details are updated correctly.
    @Test
    public void tc02updatePetTests() throws Exception {
        instanceId = Config.pt_testCase_002;
        String baseUrl = Config.hostname;
        String path = Config.petApi;
        String fileName = Config.updateFileName;
        String updatedPetName = TestData.updatedPetName;
        JsonObject jsonString = testUtils.readJsonPrepareData(fileName);
        jsonString.addProperty("id",petIdCreated);
        Response response = HttpUtils.putHttpRequest(baseUrl, path, jsonString);

        //Verify Pet is updated successfully.
        assert (response.getStatusCode() == 200);
        assert (response.getBody().asString().length() > 0);

        //Verify whether the Pet updated matches with the input given.
        JSONObject obj = (JSONObject) parser.parse(response.getBody().asString());
        petIdCreated = obj.get("id").toString();
        try{
            validation.validatePetDetails(obj,updatedPetName);
            //Update PractiTest
            status=0;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
        catch(AssertionError e){
            //Update PractiTest
            status=1;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
    }

    //TestCase-03 To Delete a Pet for PetStore and validate the Pet details are deleted correctly.
    @Test
    public void tc03deletePetTests() throws ParseException, Exception {
        instanceId = Config.pt_testCase_003;
        String baseUrl = Config.hostname;
        System.out.println("PetId->" + petIdCreated);
        String path = Config.petApi + "/" + petIdCreated;
        Response response = HttpUtils.deleteHttpRequest(baseUrl, path);

        //Verify Pet is deleted successfully.
        assert (response.getStatusCode() == 200);
        assert (response.getBody().asString().length() == 0);

        //Verify the Pet is deleted successfully by getting the Pet information using PetId.
        Response getPetResponse = HttpUtils.getHttpRequest(baseUrl,path);
        assert (getPetResponse.getStatusCode() == 404);
        JSONObject responseBodyObj = (JSONObject) parser.parse(getPetResponse.getBody().asString());
        try{
            assert (responseBodyObj.get("message").toString().equalsIgnoreCase("Pet not found"));
            //Update PractiTest
            status=0;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
        catch(AssertionError e){
            //Update PractiTest
            status=1;
            Response practiTestResponse = testUtils.updatePractitest(practiTestFileName, projectId, instanceId, status);
            assert(practiTestResponse.getStatusCode() == 200);
        }
    }
}
