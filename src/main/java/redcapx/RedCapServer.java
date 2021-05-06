package redcapx;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import util.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RedCapServer {
    public enum Format {
        JSON,
        CSV
    }
    /*
    Test
    Trying to test the connection:
    Doing a small request;
    method returns TRUE if the connection works & FALSE, if not
    */
    public boolean tryConnection(){
        HttpResponse response;
        int respCode;
        final HttpPost request= new HttpPost(Config.REDCAP_API_URL);
        String token = Config.REDCAP_TOKEN;
        HttpClient client = HttpClientBuilder.create().build();
        final List<NameValuePair> params = new ArrayList<NameValuePair>();


        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("content", "record"));
        params.add(new BasicNameValuePair("format", "json"));
        params.add(new BasicNameValuePair("type", "flat"));
        params.add(new BasicNameValuePair("csvDelimiter", ""));

        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try { //hands over the parameters to the request, e.g. token
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        try{//executing the request, returns true, if the response Code is 200
            //if the response Code is different, the connection is not as expected
            response = client.execute(request);
            respCode = response.getStatusLine().getStatusCode();
            if (respCode==200){
                //System.out.println("Connection to Server as we want *_*");
                return true;
            }else{
                System.out.println(respCode);
                //System.out.println("Something went wrong? :(");
                return false;
            }
        }catch(final Exception e){
            e.printStackTrace();
        }return false;
    }

    /*
    Sends a request to REDCap_API to get the patient data
    To change the format just change line 82, from csv to json
    Returns the Data as String
    */
    public String getData(Format inhalt){
        final List<NameValuePair> params;
        final StringBuffer result = new StringBuffer();
        final HttpPost request;
        HttpResponse response;
        int respCode;
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        HttpClient client = HttpClientBuilder.create().build();
        request = new HttpPost(redcapurl);
        String format = inhalt.toString().toLowerCase();

        //defining the parameters
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("content", "record"));
        params.add(new BasicNameValuePair("format", format));
        params.add(new BasicNameValuePair("type", "flat"));
        params.add(new BasicNameValuePair("csvDelimiter", ","));

        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try{ //hands over the parameters to the request, e.g. token
            request.setEntity(new UrlEncodedFormEntity(params));
        }catch(final Exception e) {
            e.printStackTrace();
        }

        try{ //executes the request, reads the response line by line with the string buffer
            response = client.execute(request);
            //System.out.println(response.getEntity().getContent()); //Keno idee die nicht fuktioniert
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); //Get the response
            String line = "";

            while((line = rd.readLine()) != null){
                result.append(line+"\n");
            }
            //just for certainty
            respCode = response.getStatusLine().getStatusCode();
            System.out.println(respCode);
        }catch (final Exception e) {
            e.printStackTrace();
        }
        return result.toString(); //returning the result as String for Nick and Lisamaria
    }

}

/*
Code, der es nicht geschafft hat:
    import org.apache.http.client.methods.HttpGet;
    import org.apache.http.client.utils.URLEncodedUtils;
    final HttpGet request;
    HttpGet httpget = new HttpGet(redcapurl + "?" + URLEncodedUtils.format(params, "utf-8"));
    request = new HttpGet(Config.REDCAP_API_URL);
*/

/*
Helper:
https://www.vogella.com/tutorials/ApacheHttpClient/article.html
https://hc.apache.org/httpclient-legacy/tutorial.html
https://confluence.research.cchmc.org/display/CCTSTRED/REDCap+API+Examples
https://stackoverflow.com/questions/27102996/how-to-add-namevaluepairs-for-a-httpget-request-in-android
https://stackoverflow.com/questions/53229545/how-i-can-add-token-to-http-get-request-to-rest-api
 */