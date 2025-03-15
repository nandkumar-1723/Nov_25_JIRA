package TestCases;

import io.restassured.*;
import io.restassured.response.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class LoginJira {

    @Test
    public void loginJIRA() throws IOException, ParseException {

        FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/loginJira.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);

        Response response = RestAssured.given().baseUri("http://localhost:9009")
                .body(requestBody)
                .header("Content-Type", "application/json")
                .when().post("/rest/auth/1/session")
                .then().log().all().extract().response();
    }
}
