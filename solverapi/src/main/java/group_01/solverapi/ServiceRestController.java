package group_01.solverapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import group_01.solverapi.control.Controller;
import group_01.solverapi.exceptions.*;
import group_01.solverapi.model.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@Configuration
public class ServiceRestController {
    private Controller controller;
    private ObjectMapper objectMapper;
    private Logger logger;

    public ServiceRestController(Controller controller, ObjectMapper objectMapper) {
        this.controller = controller;
        this.objectMapper = objectMapper;
        this.logger = LoggerFactory.getLogger(ServiceRestController.class);
    }

    @RequestMapping("solver")
    @PostMapping
    public ResponseEntity<String> initial(HttpServletRequest request)
    {
       InputStream stream = null;
        try {
           stream = request.getInputStream();
        } catch (IOException e) {
           logger.error("Fatal error when reading request content");
           throw  new InitializeException();
        }

        Move move;
        try {
            move = controller.makeMove(stream);
        } catch (InitializeException e) {
            logger.error("Fatal error when initializing the game model");
            throw new InitializeException();
        } catch (BadInputException e) {
            logger.error(String.format("fatal error when finding move: %s", e.getMessage()));
            throw  new BadInputException(String.format("fatal error when finding move: %s", e.getMessage()));
        }

        if (move == null) {
            return new ResponseEntity<String>("Unable to find move", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String resp = move.toString();
        if (resp == null) {
            return new ResponseEntity<String>("Move was not fully populated", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(resp, HttpStatus.ACCEPTED);
    }

    @RequestMapping("test")
    @GetMapping
    public ResponseEntity<String> test() {
        InputStream stream = null;
        try {
            File file = new File("C:\\Users\\jonas\\Documents\\GitHub\\solitaire_Solver\\solverapi\\src\\main\\java\\group_01\\solverapi\\res\\dta.txt");
            stream = new FileInputStream(file);
        } catch (IOException e) {
            System.out.println("unable to open file");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Move move = null;
        try {
           move = controller.InitializeGame(stream);
        } catch (InitializeException e) {
            System.out.println(String.format("init failed : %s", e.getMessage()));
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (move != null) {
            return new ResponseEntity<String>(move.toString(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
