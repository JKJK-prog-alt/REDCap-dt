package redcapx;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Config;


public class RedCapServerUnirest {

    public String getData(){
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        String test =null;
        try{
            HttpResponse<JsonNode> response = Unirest.post(redcapurl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .queryString("apiKey", token)
                    .field("content", "record")
                    .field("type", "flat")
                    .asJson();
            System.out.println(response);
            test = response.toString();


        }catch(final Exception e) {
            e.printStackTrace();
        }
        return test;


    }
}
