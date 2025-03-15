package Testing;


import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class revision {

    @Test
    public void readJson() throws IOException, ParseException {

//        1.To read any file using a java, then we have to use one class i.e. FileReader class
//        2. To get the function from the class, we need to create object of that class.
//        3. How to create object..? <className> refVariable = new <constructor>;
        FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/cricket.json");
//        4. Convert the json into string using the class i.e. JSONParser class
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println("Full Request body:"+requestBody);

        System.out.println("______Read the specific json key_______");
//        1. To read the specific key from the json, we need to use the class i.e. JSONObject
        JSONObject js = new JSONObject(requestBody);
        Object firstname = js.getJSONArray("groupA").getJSONObject(0).get("firstName");
        System.out.println(firstname);

//        1. To update the specific key from the json, we need to use the class i.e. JSONObject
      js.getJSONArray("groupA").getJSONObject(0).put("salary","25cr");
        System.out.println(js);
    }
}
