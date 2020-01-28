package com.petstore.tests.testlibs;

public class Config {
    public static String testEnv = System.getProperty("testEnv", "local");

    //Hostname details
    public static String hostname;
    public static String localHostName = "http://petstore.swagger.io/v2";
    public static String practiTestHostName = "https://api.practitest.com";

    //API details
    public static String petApi = "/pet";
    public static String practiTestApi = "/api/v2/projects/";
    public static String practiTestRunUpdateAPI = "/runs.json";

    //TestData fileName
    public static String createFileName = "CreatePetJsonBody.json";
    public static String updateFileName = "UpdatePetJsonBody.json";

    //PractiTest details
    public static String pt_ProjectId = "14445";
    public static String pt_testCase_001 = "22098690";
    public static String pt_testCase_002 = "22098691";
    public static String pt_testCase_003 = "22098692";
    public static String pt_api_Token = "31f027a478d98ef4e79821e4bec16a418b693585";
    public static String pt_developer_emailId = "polina@we-trade.com";
    public static String pt_fileName = "PractiTestJsonBody.json";

    public static String getHostName(){
        if(testEnv.equalsIgnoreCase("local")){
            hostname = localHostName;
        }
        return hostname;
    }
}
