package control;

import gamelogic.LogicController;
import model.Game;
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

    public void InitializeGame() throws IOException{
        cardPlacementDAO.getInitialGameState();
        //TODO update game model
    }

    public void getCurrentGameState() throws IOException {
        cardPlacementDAO.getCurrentGameState();
        //TODO update game model
    }
}
