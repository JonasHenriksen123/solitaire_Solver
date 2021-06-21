package group_01.solverapi.picrecaccess;

import com.sun.xml.messaging.saaj.util.ByteInputStream;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@Component
public class CardPlacementDAO {

    public CardStateDTO getCurrentGameState(InputStream stream) throws IOException, ParseException {
        String uri = "http://localhost:8000/";
        URL url = new URL(uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String method = "POST";
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod(method);
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            StreamUtils.copy(stream, os);
            os.flush();
        }

        con.getResponseCode();

        String response = null;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), Charset.defaultCharset()))) {

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
