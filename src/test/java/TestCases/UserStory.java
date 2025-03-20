package TestCases;

import io.restassured.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class UserStory {

    private String cookie;
    private String issueId;

    @Test(priority = 0)
    public void loginJira() throws IOException, ParseException {

//    Read the loginJira.json file
    FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/loginJira.json");
    JSONParser jp = new JSONParser();
    String requestBody = jp.parse(fr).toString();

    Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
            .header("Content-Type", "application/json")
            .when().post("/rest/auth/1/session")
            .then().extract().response();

        System.out.println("Status code - "+response.getStatusCode());
        System.out.println("Status Line "+response.getStatusLine());
        System.out.println("Response "+response.asString());

//    To get the JSESSION ID from the response ( By using JSONObject class)
    JSONObject js = new JSONObject(response.asString());
     cookie = "JSESSIONID="+js.getJSONObject("session").get("value").toString();
}

@Test(priority = 1)
    public void createJira() throws IOException, ParseException {

    FileReader fr = new FileReader("/home/nandkumar/Videos/Nov_25_JIRA/src/main/java/com/arise/Files/CreateUserStory.json");
    JSONParser jp = new JSONParser();
    String requestBody = jp.parse(fr).toString();

    Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
            .header("Content-Type", "application/json")
            .header("Cookie", cookie).when().post("/rest/api/2/issue")
            .then().log().all().extract().response();

    JSONObject js = new JSONObject(response.asString());
     issueId = js.get("key").toString();


}

@Test(priority = 3)
    public void getUserStory(){
    Response response = RestAssured.given().baseUri("http://localhost:9009")
            .header("Content-Type", "application/json").header("Cookie", cookie)
            .queryParam("fields","status").queryParam("fields","priority")
            .when().get("/rest/api/2/issue/"+issueId).then().log().all().extract().response();


}

@Test(priority = 4)
    public void updateUserStory() throws IOException, ParseException {
    FileReader fr = new FileReader("/home/nandkumar/Videos/Nov_25_JIRA/src/main/java/com/arise/Files/UpdateUserStory.json");
    JSONParser jp = new JSONParser();
    String requestBody = jp.parse(fr).toString();

    Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
            .header("Content-Type", "application/json")
            .header("Cookie", cookie)
            .when().put("/rest/api/2/issue/"+issueId)
            .then().log().all().extract().response();
}

@Test(priority = 5)
    public void deleteUserStory(){
    Response response = RestAssured.given().baseUri("http://localhost:9009").header("Content-Type", "application/json")
            .header("Cookie", cookie).when().delete("/rest/api/2/issue/" + issueId)
            .then().log().all().extract().response();
}

}
