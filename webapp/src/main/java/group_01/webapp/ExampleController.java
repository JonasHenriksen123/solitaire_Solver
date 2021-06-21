package group_01.webapp;

import group_01.webapp.ServiceAPI.ISolitaireService;
import group_01.webapp.services.SolitaireService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.handler.ExceptionHandlingWebHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.stream.Stream;

@RestController
@Configuration
public class ExampleController {
    private ISolitaireService solitaireService;

    public ExampleController(SolitaireService solitaireService) {
        this.solitaireService = solitaireService;
    }

    @RequestMapping("solve")
    @PostMapping
    public ResponseEntity POST(HttpServletRequest request) {
        InputStream stream;
        try {
            stream = request.getInputStream();
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            solitaireService.Solve(stream);
        } catch (MalformedURLException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }
}
