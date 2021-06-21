package group_01.solverapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import group_01.solverapi.control.Controller;
import group_01.solverapi.exceptions.*;
import group_01.solverapi.model.Move;
import group_01.solverapi.picrecaccess.CardPlacementDAO;
import group_01.solverapi.picrecaccess.CardStateDTO;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;

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
    public ResponseEntity initial(HttpServletRequest request)
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
            move = controller.InitializeGame(stream);
        } catch (InitializeException e) {
            logger.error("Fatal error when initializing the game model");
            throw new InitializeException();
        }

        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.accepted();
        bodyBuilder.body(move);
        return bodyBuilder.build();
    }

    @RequestMapping("makemove")
    @GetMapping
    public ResponseEntity makeMove(HttpServletRequest request) {
        InputStream stream = null;
        try {
            stream = request.getInputStream();
        } catch (IOException e) {
            logger.error("fatal error when reading request content");
            throw new BadInputException("Could not read request content");
        }

        Move move;
        try {
            move = controller.makeMove(stream);
        } catch (BadInputException e) {
            logger.error(String.format("fatal error when finding move: %s", e.getMessage()));
            throw  new BadInputException(String.format("fatal error when finding move: %s", e.getMessage()));
        }

        if (move == null) {
            //the game was lost bad lucks
            //TODO tell the user we lost
        }

        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.accepted();
        bodyBuilder.body(move);
        return bodyBuilder.build();
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

        CardStateDTO cstate = null;
        try {
            cstate = new CardPlacementDAO().getCurrentGameState(stream);
        } catch (ParseException e) {
            System.out.println(String.format("unable to parse : %s", e.getMessage()));
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("good response", HttpStatus.ACCEPTED);
    }
}
