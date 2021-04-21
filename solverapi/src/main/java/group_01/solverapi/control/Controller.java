package group_01.solverapi.control;

import group_01.solverapi.gamelogic.LogicController;
import group_01.solverapi.model.Game;
import group_01.solverapi.picrecaccess.CardStateDTO;
import group_01.solverapi.picrecaccess.ICardPlacementDAO;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Controller {
    private Game game;
    private LogicController logicController;
    private ICardPlacementDAO cardPlacementDAO;

    public Controller(ICardPlacementDAO cardPlacementDAO, Game game, LogicController logicController) {
        this.game = game;
        this.logicController = logicController;
        this.cardPlacementDAO = cardPlacementDAO;
    }

    public void InitializeGame() throws Exception{
        CardStateDTO cardState = cardPlacementDAO.getInitialGameState();
        game.initializeModel(cardState);
    }

    public void getCurrentGameState() throws Exception {
        CardStateDTO cardState = cardPlacementDAO.getCurrentGameState();
        game.updateModel(cardState);
    }
}
