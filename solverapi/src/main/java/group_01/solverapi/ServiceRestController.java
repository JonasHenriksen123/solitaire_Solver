package group_01.solverapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import group_01.solverapi.control.Controller;
import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.model.Move;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("initialize")
    @PostMapping
    public ResponseEntity initial(String json)
    {
        ICardStateDTO cardState;
        try {
            cardState = objectMapper.readValue(json, ICardStateDTO.class);
        } catch (JsonProcessingException e) {
           logger.error("Fatal error when decoding input from client");
           throw new BadInputException("Fatal error when decoding input from user");
        }

        Move move;
        try {
            move = controller.InitializeGame(cardState);
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
    public ResponseEntity makeMove(String json) {
        ICardStateDTO cardState;
        try {
            cardState = objectMapper.readValue(json, ICardStateDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("fatal error when decoding input from client");
            throw new BadInputException("Fatal error when decoding input from user");
        }

        Move move;
        try {
            move = controller.makeMove(cardState);
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


}
