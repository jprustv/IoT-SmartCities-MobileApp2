package com.example.jonathan.iot_smartcities_mobileapp2;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jonathan on 11/08/2016.
 */
public class Connection {
    // JSON URL for testing:
    // https://api.myjson.com/bins/2ognh
    // https://api.myjson.com/bins/3g9fj

    private final String USER_AGENT = "Mozilla/5.0";
    private final static String debugPrefix = "<Connection> ";
    // HTTP GET request
    public List<PIPEInstance> sendGet() throws Exception {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = "https://api.myjson.com/bins/3g9fj";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println(debugPrefix + "Sending 'GET' request to URL : " + url);
        System.out.println(debugPrefix + "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //System.out.println(response.toString());

        List<PIPEInstance> found = findAllItems(new JSONArray(response.toString()));

        return found;
    }

    public List<PIPEInstance> findAllItems(JSONArray response) {

        List<PIPEInstance> found = new LinkedList<PIPEInstance>();

        try {


            for (int i = 0; i < response.length(); i++) {
                JSONObject obj = response.getJSONObject(i);
                found.add(new PIPEInstance(obj.getString("timestamp"),
                                           i+1,
                                           obj.getDouble("temperatura"),
                                           obj.getDouble("amonia"),
                                           obj.getDouble("oxigenioDissolvido"),
                                           obj.getDouble("ph"),
                                           obj.getDouble("nitrito"),
                                           obj.getDouble("solidosSuspensos"),
                                           obj.getDouble("co2"),
                                           obj.getDouble("salinidade")));
            }

        } catch (JSONException e) {
            // handle exception
        }

        return found;
    }

}
