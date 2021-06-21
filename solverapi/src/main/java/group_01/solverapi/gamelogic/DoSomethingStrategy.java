package group_01.solverapi.gamelogic;

import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
import org.springframework.stereotype.Component;

@Component
public class DoSomethingStrategy {

    private Game game;
    private int conxecutiveDraws = 0;
    private int unuasbleConxecutiveRandomMoves = 0;

    public DoSomethingStrategy(Game game) {
        this.game = game;
    }

    public Move execute() {
        Move bestMove;

        bestMove = drawFromPile();
        if (bestMove != null) {
            return bestMove;
        }

        bestMove = randomMove();
        if (bestMove != null) {
            return bestMove;
        }

        return null;
    }

    private Move drawFromPile() {
        int cardcount = game.DrawCount();
        if (cardcount == 0 || cardcount < conxecutiveDraws) {
            return null;
        }
        conxecutiveDraws++;
        return new Move(Move.MoveType.DRAW);
    }

    private Move randomMove() {
        if (unuasbleConxecutiveRandomMoves > 5) {
            return null;
        }
        Move move = null;

        //TODO find a way to find a random "usefull" move

        return move;
    }
}
