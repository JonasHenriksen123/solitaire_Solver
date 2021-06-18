package group_01.webapp.ServiceAPI;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.stream.Stream;

@Component
public interface ISolitaireService {
    void Solve(InputStream stream) throws IOException;
}
