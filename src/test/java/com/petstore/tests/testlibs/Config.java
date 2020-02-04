package com.petstore.tests.testlibs;

public class Config {
    public static String testEnv = System.getProperty("testEnv", "local");

    //Hostname details
    public static String hostname;
    public static String localHostName = "http://petstore.swagger.io/v2";

    //API details
    public static String petApi = "/pet";

    //TestData fileName
    public static String createFileName = "CreatePetJsonBody";
    public static String updateFileName = "UpdatePetJsonBody";

    public static String getHostName(){
        if(testEnv.equalsIgnoreCase("local")){
            hostname = localHostName;
        }
        return hostname;
    }
}
