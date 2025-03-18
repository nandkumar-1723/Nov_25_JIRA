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

    @Test
    public void loginJira() throws IOException, ParseException {

//    Read the loginJira.json file
    FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/loginJira.json");
    JSONParser jp = new JSONParser();
    String requestBody = jp.parse(fr).toString();

    Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
            .header("Content-Type", "application/json")
            .when().post("/rest/auth/1/session")
            .then().log().all().extract().response();

//    To get the JSESSION ID from the response ( By using JSONObject class)
    JSONObject js = new JSONObject(response.asString());
     cookie = "JSESSIONID="+js.getJSONObject("session").get("value").toString();
}

@Test
    public void createJira() throws IOException, ParseException {

    FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/CreateJira.json");
    JSONParser jp = new JSONParser();
    String requestBody = jp.parse(fr).toString();

    Response response = RestAssured.given().baseUri("http://localhost:9009").body(requestBody)
            .header("Content-Type", "application/json")
            .header("Cookie", cookie).when().post("/rest/api/2/issue")
            .then().log().all().extract().response();

}
}
