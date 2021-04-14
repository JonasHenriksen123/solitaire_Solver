package control;

import gamelogic.LogicController;
import model.Game;
import picrecaccess.CardStateDTO;
import picrecaccess.ICardPlacementDAO;

import java.io.IOException;

public class Controller {
    private Game game;
    private LogicController logicController;
    private ICardPlacementDAO cardPlacementDAO;

    public Controller(ICardPlacementDAO cardPlacementDAO) {
        game = new Game();
        logicController = new LogicController();
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
