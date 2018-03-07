package nl.alten.endegraaf.openkm.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;

public class RestClient {

    /*
        client call
        return type --> int

        functions:
            int count subfolders
            void create new folders

     */

    private static final String FLD_UUID = "ca4ac3e2-4e7b-4c7a-9961-3b18ac5eaf2b";
    private static final String DEMO_USER = "okmAdmin";
    private static final String DEMO_PASS = "admin";

    public int CountSubFoldersInRoot() {

        HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        try {
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

                try {
                    gsonObject.get("folders").getAsJsonObject();
                } catch (IllegalStateException e) {
                    throw new IllegalStateException("Expected a Json Object got something else. Check the contents of the root folder.");
                }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hmap.size();
    }



}
