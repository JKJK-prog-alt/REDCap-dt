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

public class RecievingMetaData {
    public String getMetaData(){
        final List<NameValuePair> params;
        final StringBuffer result = new StringBuffer();
        final HttpPost request;
        HttpResponse response;
        int respCode;
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        HttpClient client = HttpClientBuilder.create().build();
        request = new HttpPost(redcapurl);

        //defining the parameters
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("content", "metadata"));
        params.add(new BasicNameValuePair("format", "json"));
        params.add(new BasicNameValuePair("returnFormat", "json"));

        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try{ //hands over the parameters to the request, e.g. token
            request.setEntity(new UrlEncodedFormEntity(params));
        }catch(final Exception e) {
            e.printStackTrace();
        }

        try{ //executes the request, reads the response line by line with the string buffer
            response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); //Get the response
            String line = "";

            while((line = rd.readLine()) != null){
                result.append(line).append(","); //Geht das so??
            }
            //just for certainty
            respCode = response.getStatusLine().getStatusCode();
            System.out.println(respCode);
        }catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result.toString();
    }

}

