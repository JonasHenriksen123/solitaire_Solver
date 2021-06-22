package group_01.solverapi.control;

import group_01.solverapi.exceptions.*;
import group_01.solverapi.gamelogic.*;
import group_01.solverapi.model.*;
import group_01.solverapi.picrecaccess.*;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.io.*;

@Component
public class Controller {
    private Game game;
    private LogicController logicController;
    private ValidateController validateController;
    private CardPlacementDAO cardPlacementDAO;

    private Boolean init = false;

    public Controller(Game game, LogicController logicController, ValidateController validateController, CardPlacementDAO cardPlacementDAO) {
        this.game = game;
        this.logicController = logicController;
        this.validateController = validateController;
        this.cardPlacementDAO = cardPlacementDAO;
    }

    public Move InitializeGame(InputStream stream) throws InitializeException {
        CardStateDTO cardState = null;
        try {
            cardState = cardPlacementDAO.getCurrentGameState(stream);
        } catch (IOException e) {
            throw new InitializeException();
        } catch (ParseException e) {
            throw new BadInputException("Unable to parse game state with JSON-Simple");
        }

        validateController.validateinit(cardState);
        game.initializeModel(cardState);

        Move move = logicController.makeMove();
        if (move == null) {
            //this is real bad
            throw new BadInputException("Unable to find move after initialization");
        }
        return move;
    }

    public Move makeMove(InputStream stream) throws BadInputException {
        if (!init) {
            Move move = this.InitializeGame(stream);
            init = true;
            return move;
        }

        CardStateDTO cardState = null;
        try {
            cardState = cardPlacementDAO.getCurrentGameState(stream);
        } catch (IOException e) {
            throw new BadInputException("Unable to parse game state");
        } catch (ParseException e) {
            throw new BadInputException("Unable to parse game state with JSON-Simple");
        }
        validateController.validate(cardState);
        game.updateModel(cardState);

        return logicController.makeMove();
    }
}
