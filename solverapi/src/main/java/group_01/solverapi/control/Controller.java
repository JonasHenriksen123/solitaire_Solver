package group_01.solverapi.control;

import com.sun.xml.messaging.saaj.util.ByteInputStream;
import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.gamelogic.LogicController;
import group_01.solverapi.gamelogic.ValidateController;
import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
import group_01.solverapi.picrecaccess.CardPlacementDAO;
import group_01.solverapi.picrecaccess.CardStateDTO;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class Controller {
    private Game game;
    private LogicController logicController;
    private ValidateController validateController;
    private CardPlacementDAO cardPlacementDAO;

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
        CardStateDTO cardState = null;
        try {
            cardState = cardPlacementDAO.getCurrentGameState(stream);
        } catch (IOException e) {
            throw new BadInputException("Unable to parse game state");
        }
        validateController.validate(cardState);
        game.updateModel(cardState);

        return logicController.makeMove();
    }

    public void getCurrentGameState(ICardStateDTO cardState) {
        game.updateModel(cardState);
    }
}
