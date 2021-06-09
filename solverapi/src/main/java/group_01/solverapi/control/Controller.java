package group_01.solverapi.control;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.gamelogic.LogicController;
import group_01.solverapi.gamelogic.ValidateController;
import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.stereotype.Component;

@Component
public class Controller {
    private Game game;
    private LogicController logicController;
    private ValidateController validateController;

    public Controller(Game game, LogicController logicController, ValidateController validateController) {
        this.game = game;
        this.logicController = logicController;
        this.validateController = validateController;
    }

    public Move InitializeGame(ICardStateDTO cardState) throws InitializeException {
        validateController.validateinit(cardState);
        game.initializeModel(cardState);

        Move move = logicController.makeMove();
        if (move == null) {
            //this is real bad
            throw new BadInputException("Unable to find move after initialization");
        }
        return move;
    }

    public Move makeMove(ICardStateDTO cardState) throws BadInputException {
        validateController.validate(cardState);
        game.updateModel(cardState);

        return logicController.makeMove();
    }

    public void getCurrentGameState(ICardStateDTO cardState) {
        game.updateModel(cardState);
    }
}
