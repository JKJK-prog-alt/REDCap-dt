package redcapx;
import kong.unirest.JsonNode;
import kong.unirest.HttpResponse;;
import kong.unirest.Unirest;
import util.Config;


public class RedCapServerUnirest {
    /*
    Trying to test the connection:
    Doing a small request;
    method returns TRUE if the connection works & FALSE, if not
    */
    public boolean tryConnection(){
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        try{
            HttpResponse<JsonNode> response = Unirest.post(redcapurl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("token", token)
                    .field("content", "record")
                    .field("type", "flat")
                    .field("format", "json")
                    .asJson();

            int status = response.getStatus();
            System.out.println(status);
            if(status==200){
                System.out.print("Server-Connection = ");
                return true;
            }else{
                System.out.print("Server-Connection = ");
                return false;
            }

        }catch(final Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
    Sends a request to REDCap_API to get the patient data
    Returns the Data as JsonNode
    */
    public JsonNode getData(){
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        JsonNode responsebody = null;
        try{
            HttpResponse<JsonNode> response = Unirest.post(redcapurl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("token", token)
                    .field("content", "record")
                    .field("type", "flat")
                    .field("format", "json")
                    .asJson();
            System.out.println(response.getStatusText());
            responsebody=response.getBody();
        }catch(final Exception e) {
            e.printStackTrace();
        }
        return responsebody;
    }
    /*
    Sends a request to REDCap_API to get the patient metadata
    Returns the MetaData as JsonNode
    */
    public JsonNode getMetaData(){
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        JsonNode responsebody = null;
        try{
            HttpResponse<JsonNode> response = Unirest.post(redcapurl)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("token", token)
                    .field("content", "metadata")
                    .field("type", "flat")
                    .field("format", "json")
                    .asJson();
            System.out.println(response.getStatusText());
            responsebody=response.getBody();
        }catch(final Exception e) {
            e.printStackTrace();
        }
        return responsebody;
    }
}
