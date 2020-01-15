package com.petstore.tests.testlibs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.*;

import io.restassured.response.Response;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestUtils {

    JSONParser parser = new JSONParser();

    public JsonObject readJsonPrepareData(String fileName) throws IOException {
        Gson gson = new Gson();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
        return jsonObject;
    }

    public JSONObject readJson(String fileName) throws IOException,ParseException, FileNotFoundException {
        JSONObject data = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            //Use JSONObject for simple JSON and JSONArray for array of JSON.
            data = (JSONObject) parser.parse(
                    new FileReader("./src/test/resources/"+fileName));//path to the JSON file.
            System.out.println(data.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
    public Response updatePractitest(String fileName, String projectId, String instanceId, int status) throws Exception, IOException{
        Boolean statusUpdated = true;
        String baseURL = Config.practiTestHostName;
        String api = Config.practiTestApi;
        String runApi = Config.practiTestRunUpdateAPI;
        String path = api+projectId+runApi;
        String apiToken = Config.pt_api_Token;
        String developerEmailId = Config.pt_developer_emailId;
        JSONObject jsonBody = readJson(fileName);
        JSONObject parsingBody = (JSONObject) parser.parse(jsonBody.toJSONString());
        JSONObject dataBody = (JSONObject) parsingBody.get("data");
        JSONObject attributeJsonBody = (JSONObject) dataBody.get("attributes");
        attributeJsonBody.put("exit-code", status);
        attributeJsonBody.put("instance-id", instanceId);
        System.out.println(parsingBody);
        return HttpUtils.postPractiTestHttpRequest(baseURL,path,apiToken, developerEmailId, parsingBody);
    }
}
