package dev.mdma.simpleauth.utils.response;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

public class Response {
    public static void getPostResponse(String url, String query, Consumer<ResponseContainer> callback) throws IOException {
        URL urlObject = new URL(url);

        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-length", String.valueOf(query.length()));
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setConnectTimeout(5000);

        connection.setDoOutput(true);
        connection.setDoInput(true);

        DataOutputStream output = new DataOutputStream(connection.getOutputStream());
        output.writeBytes(query);
        output.close();

        DataInputStream input = new DataInputStream(connection.getInputStream());
        StringBuilder stringBuilder = new StringBuilder();

        int c = input.read();

        while (c != -1) {
            stringBuilder.append((char) c);
            c = input.read();
        }

        String response = stringBuilder.toString();

        input.close();

        if (connection.getResponseCode() == 200) {
            callback.accept(new Gson().fromJson(response, ResponseContainer.class));
        } else {
            callback.accept(null);
        }
    }
}
