package nl.alten.endegraaf.openkm.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;

public class JavaRestClient {

    private static final String FLD_UUID = "f43cf328-dfda-4ba3-925e-ce36a0607dc0";
    private static final String DEMO_USER = "okmAdmin";
    private static final String DEMO_PASS = "admin";

    public int CountSubFoldersInRoot() {
        HashMap<Integer, String> hmap = new HashMap<Integer, String>();;
        try {
            // Root

            URL url = new URL("http://localhost:8080/OpenKM/services/rest/folder/getChildren?fldId=" + FLD_UUID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(DEMO_USER, DEMO_PASS.toCharArray());
                }
            });
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Gson gson = new Gson();
                JsonObject gsonObject = gson.fromJson(br, JsonObject.class);
                JsonObject jsonObject = gson.fromJson(gsonObject.get("folders"), JsonObject.class);
                JsonArray array = jsonObject.get("folder").getAsJsonArray();
                // unpack elements
                for (int i = 0; i < array.size(); i++) {
                    hmap.put(i, array.get(i).toString());
                }
            } else {
                System.err.println("Failed : HTTP error code : " + conn.getResponseCode());
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hmap.size();
    }
}
