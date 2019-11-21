package com.petstore.tests.testlibs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import com.google.gson.*;

public class TestUtils {

    public JsonObject readJsonPrepareData(String fileName) throws IOException {
        Gson gson = new Gson();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
        return jsonObject;
    }
}
