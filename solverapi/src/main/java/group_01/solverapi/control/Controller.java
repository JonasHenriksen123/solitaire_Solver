package group_01.solverapi.control;

import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.gamelogic.LogicController;
import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
import group_01.solverapi.picrecaccess.CardStateDTO;
import group_01.solverapi.picrecaccess.ICardPlacementDAO;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Controller {
    private Game game;
    private LogicController logicController;

    public Controller(Game game, LogicController logicController) {
        this.game = game;
        this.logicController = logicController;
    }

    public Move InitializeGame(ICardStateDTO cardState) throws InitializeException {
        game.initializeModel(cardState);
        //TODO get the first move
        return null;
    }

    public void getCurrentGameState(ICardStateDTO cardState) {
        game.updateModel(cardState);
    }
}
