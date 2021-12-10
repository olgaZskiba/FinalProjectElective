package com.olgaskyba.elective.recaptcha;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class VerifyUtils {
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SITE_KEY = "6Lc-OIIdAAAAADH5myha9uMqexc5gUmmfkSgW1M2";
    private static final String SECRET_KEY = "6Lc-OIIdAAAAAO6D7LfKiP8mJnn5Pcj3RWPwyGN1";


    public static boolean verify(String gRecaptchaResponse){
        if (gRecaptchaResponse==null||gRecaptchaResponse.length()==0){
            return false;
        }

        try{
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            //open the connection for verifyURL
            HttpsURLConnection connection = (HttpsURLConnection) verifyUrl.openConnection();
            //add Header information to Request, for prepare sending to server
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //data will be send to server
            String postParams = "secret="+SECRET_KEY+"&response="+gRecaptchaResponse;
            // Send Request
            connection.setDoOutput(true);
            //receive OutputStream to server
            //write data to OutputStream
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postParams.getBytes(StandardCharsets.UTF_8));

            outputStream.flush();
            outputStream.close();

            //return response from server
            int responseCode = connection.getResponseCode();
            //receive InputStream for to read data from server's response
            InputStream inputStream = connection.getInputStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            boolean success = jsonObject.getBoolean("success");
            return success;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
