package group_01.webapp.services;

import group_01.webapp.ServiceAPI.ISolitaireService;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Component
public class SolitaireService implements ISolitaireService {

    @Override
    public void Solve(InputStream stream) throws IOException {
        URL url = new URL("localhost:8081/solver");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("content-type", "image/jpeg: utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] bytes = StreamUtils.copyToByteArray(stream);
            os.write(bytes);
        }

        connection.connect();

        String response = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {

            StringBuilder strBuild = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                strBuild.append(responseLine.trim());
            }

            response = strBuild.toString();
        }

        return;
    }
}
