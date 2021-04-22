package redcapx;
/*
FragenSammlung:
Verwirrung zwischen post/get --> wir wollen ja nichts posten sondern bekommen?
https://www.vogella.com/tutorials/ApacheHttpClient/article.html
Testen??
get gib daten

post ich schicke udn krieg vllt zurück
update bestehender datensatz bekommen
delete loeschen

Notizen:
Gruppe 2: ArrayList.ToString --> String uebergeben
https://hc.apache.org/httpclient-legacy/tutorial.html
 */

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import util.Config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RecievingData {

    public void tryConnection(){ //Work with response code
        //Abfrage ueber Daten einfach
        HttpResponse resp;
        int respCode;
        String token = Config.REDCAP_TOKEN;
        HttpClient client = HttpClientBuilder.create().build();

    }

    public String getData(){

        final List<NameValuePair> params;
        final StringBuffer result = new StringBuffer();
        //final HttpGet request;
        final HttpPost request;
        HttpResponse response;
        int respCode;
        String token = Config.REDCAP_TOKEN;
        String redcapurl = Config.REDCAP_API_URL;
        HttpClient client = HttpClientBuilder.create().build();
        //request = new HttpGet(Config.REDCAP_API_URL);
        request = new HttpPost(Config.REDCAP_API_URL);

        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("token", token));
        params.add(new BasicNameValuePair("content", "record"));
        params.add(new BasicNameValuePair("format", "json"));
        params.add(new BasicNameValuePair("type", "flat"));
        params.add(new BasicNameValuePair("csvDelimiter", ""));
        //HttpGet httpget = new HttpGet(redcapurl + "?" + URLEncodedUtils.format(params, "utf-8"));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");


        try {
            request.setEntity(new UrlEncodedFormEntity(params));
        } catch (final Exception e) {
            e.printStackTrace();
        }



        try {
            response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent())); //Get the response
            String line = "";
            while((line = rd.readLine()) != null){
                result.append(line);
            }

            respCode = response.getStatusLine().getStatusCode();
            System.out.println(respCode);


        } catch (final Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
