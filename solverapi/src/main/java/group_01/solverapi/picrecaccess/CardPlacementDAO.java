package group_01.solverapi.picrecaccess;

import com.sun.xml.messaging.saaj.util.ByteInputStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Component
public class CardPlacementDAO {

    public CardStateDTO getCurrentGameState(InputStream stream) throws IOException {
        String uri = "localhost:8000";
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String method = "POST";
        con.setRequestMethod(method);
        con.setRequestProperty("content-type", "image/jpeg: utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = StreamUtils.copyToByteArray(stream);
            os.write(input, 0, input.length);
        }

        String response = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

            StringBuilder strBuild = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                strBuild.append(responseLine.trim());
            }

           response = strBuild.toString();
        } catch (IOException e) {
            //do nothing
        }

        if (response == null || response.isEmpty()) {
            throw new IOException("Unable to get a response from picture analysis");
        }

        return new CardStateDTO(response);
    }
}
