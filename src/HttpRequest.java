import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

/**
 * Created by laurenz on 06.02.17.
 */
public class HttpRequest {
    private final String USERAGENT = "Mozilla/5.0";
    //default parameters for a get request:
    /*
    Accept: text/html;q=0.9,*\/*;q=0.8;
    Accept-Language en-US,en;q=0.5
    Accept-Encoding gzip, deflate
    Connection keep-alive
    http Port = 80
    https Port = 443
     */
    public String sendGet(URL url, TreeMap<String,String> properties) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USERAGENT);
        //add more parameters here
        if (properties != null) {
            for (String key : properties.keySet()) {
                con.setRequestProperty(key, properties.get(key));
            }
        }

        //send request
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String sendPost(URL url, TreeMap<String,String> properties, String urlParameters) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USERAGENT);
        if (properties != null) {
            for (String key : properties.keySet()) {
                con.setRequestProperty(key, properties.get(key));
            }
        }

        //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }

}
//group wrapper -> div toggleAll -> <fieldset> Tag -> ordered List -> 5. Element = Anmeldedatum

//element über id holen "examDateListForm:j_id_44:1:appBegin;