package redcapx;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Config;


public class RedCapServerUnirest {

    public void getData(){
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;

        try{
            HttpResponse<JsonNode> response = Unirest.post(redcapurl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .queryString("apiKey", "token")
                    .field("content", "record")
                    .field("type", "flat")
                    .asJson();

        }catch(final Exception e) {
            e.printStackTrace();
        }


    }
}
