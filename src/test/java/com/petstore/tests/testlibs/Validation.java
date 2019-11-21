package com.petstore.tests.testlibs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Validation {
    JSONParser parser = new JSONParser();

    //This function validates the Pet details.
    public void validatePetDetails(JSONObject obj, String petName) throws ParseException {
        assert (obj.get("id").toString().length() > 0);
        JSONObject category = (JSONObject) parser.parse(obj.get("category").toString());
        assert (category.get("id").toString().equalsIgnoreCase(TestData.categoryId));
        assert (category.get("name").toString().equalsIgnoreCase(TestData.categoryName));
        assert (obj.get("name").toString().equalsIgnoreCase(petName));
        JSONArray photoUrls = (JSONArray) parser.parse(obj.get("photoUrls").toString());
        assert (photoUrls.get(0).toString().equalsIgnoreCase(TestData.petPhotoUrls));
        JSONArray tags = (JSONArray) parser.parse(obj.get("tags").toString());
        JSONObject tagsObj = (JSONObject) parser.parse(tags.get(0).toString());
        assert (tagsObj.get("id").toString().equalsIgnoreCase(TestData.petTagId));
        assert (tagsObj.get("name").toString().equalsIgnoreCase(TestData.petTagName));
        assert (obj.get("status").toString().equalsIgnoreCase(TestData.getPetAvailableStatus));
    }
}
