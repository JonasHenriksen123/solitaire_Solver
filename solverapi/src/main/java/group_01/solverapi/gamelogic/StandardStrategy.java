package group_01.solverapi.gamelogic;

import group_01.solverapi.model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class StandardStrategy {

    private Game game;

    public Move execute(Game game) {
        this.game = game;

        Move selectBestMove;

        //1) træk fra bunke
        selectBestMove = playStackEmpty();
        if (selectBestMove != null) {
            return new Move(Move.MoveType.DRAW);
        }

        //2) flyt es/to til mulig placering
        selectBestMove = moveAceOrTwo();
        if (selectBestMove != null) {
            return selectBestMove;
        }


        //3) flyt altid række med flest underliggende kort
        selectBestMove = freeMostFaceDownCards();
        if (selectBestMove != null) {
            return selectBestMove;
        }

        //4) flyt kort som skaber mulighed for nyt træk eller fritlægger underliggende kort
        selectBestMove = setupFaceDownCardFreeingMove();
        if (selectBestMove != null) {
            return selectBestMove;
        }


        //5) skab ikke en tom række, hvis en konge ikke er tilgængelig
        selectBestMove = kingToEmptyStack();
        if (selectBestMove != null) {
            return selectBestMove;
        }


        //6) vælg kongens suit efter mulige træk på hånden
        selectBestMove = faceDownCardFreeingMove();
        if (selectBestMove != null) {
            return selectBestMove;
        }
        return null;
    }

    private Move playStackEmpty() {
        return game.playStackEmpty() ? new Move(Move.MoveType.DRAW) : null;
    }

    private Move faceDownCardFreeingMove() {
        throw new NotImplementedException();
    }

    private Move kingToEmptyStack() {
        Move move = null;

        if (game.hasEmptyBottomStack()) {
            if (game.getFreeKing()) {
                //TODO make move
            }
        }
        return move;
    }

    private Move setupFaceDownCardFreeingMove() {
        throw new NotImplementedException();
    }

    private Move freeMostFaceDownCards() {
        throw new NotImplementedException();
    }

    private Move moveAceOrTwo() {
        throw new NotImplementedException();
    }

}
