package Testing;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadJsonFile {

    static public void main(String[] args) throws IOException, ParseException {
//         to read the json file data.
        FileReader fr = new FileReader("/home/nandkumar/Videos/restAssured_Nov/src/main/java/com/arise/Files/cricket.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();
        System.out.println(requestBody);

        // to read the specific key from the json file.
        JSONObject js = new JSONObject(requestBody);

//        to get the firstname
        String firstName = js.getJSONArray("groupB").getJSONObject(0).get("firstName").toString();
        System.out.println("Firstname is- "+firstName);

//        to get the lastname of virat
        String lastName = js.getJSONArray("groupA").getJSONObject(0).get("lastName").toString();
        System.out.println("Lastname is - "+ lastName);


//        update KL rahul's IPL team
        js.getJSONArray("groupB").getJSONObject(0).put("team","LSG");
        System.out.println(js);


    }
}
