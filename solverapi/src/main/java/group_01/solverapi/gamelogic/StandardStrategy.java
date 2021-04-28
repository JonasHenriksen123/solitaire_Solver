package group_01.solverapi.gamelogic;

import group_01.solverapi.exceptions.NotFoundException;
import group_01.solverapi.model.*;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class StandardStrategy {

    private Game game;

    public StandardStrategy(Game game) {
         this.game = game;
     }

    public Move execute() {
        Move selectBestMove;

        //1) træk fra bunke
        selectBestMove = playStackEmpty();
        if (selectBestMove != null) {
            return selectBestMove;
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
        Move move = null;
        try {
            if (game.hasUnturnedBottomStack()) {
                int stack = game.getUnturnedBottomStack();
                move = new Move(Move.MoveType.TURN);

                move.setStartPosition(stack, Position.StackRow.BOTTOM_STACKS);
            }
        } catch (NotFoundException e) {
            System.out.println("hit exception at faceDownCardFreeingMove strategy");
            e.printStackTrace();
            move = null;
        }
        return move;
    }

    private Move kingToEmptyStack() {
        Move move = null;
        try {
            if (game.hasEmptyBottomStack()) {
                if (game.hasFreeKing()) {
                    Card king = game.getFreeKing();
                    int stack = game.getEmptyBottomStack();
                    move = new Move(Move.MoveType.KING_MOVE);

                    move.setCard(king);
                    move.setStartPosition(king.getPosition(), king.getStackRow());
                    move.setTargetPosition(stack, Position.StackRow.BOTTOM_STACKS);
                }
            }
        } catch (NotFoundException e) {
            System.out.println("hit exception at kingToEmptyStack strategy.");
            e.printStackTrace();
            move = null;
        }
        return move;
    }

    private Move setupFaceDownCardFreeingMove() {
        Move move = null;
        try {
            //TODO implement strategy
        } catch (NotFoundException e) {
            System.out.println("hit exception at setupDownCardFreeingMove strategy");
            e.printStackTrace();
            move = null;
        }
        return move;
    }

    private Move freeMostFaceDownCards() {
        Move move = null;
        try {
            //TODO implement strategy
        } catch (NotFoundException e) {
            System.out.println("hit exception at freeMostFaceDownCards strategy");
            e.printStackTrace();
            move = null;
        }
        return move;
    }

    private Move moveAceOrTwo() {
        Move move = null;
        try {
            if (game.hasFreeAce()) {
                Card ace = game.getFreeAce();
                int stack = game.getEmptyTopStack();
                move = new Move(Move.MoveType.MOVE);

                move.setCard(ace);
                move.setStartPosition(ace.getPosition(), ace.getStackRow());
                move.setTargetPosition(stack, Position.StackRow.TOP_STACKS);
            } else {
                if (game.hasFreeCard(2)) {
                    Card[] cards = game.getFreeCards(2);
                    if (cards.length != 0) {
                        for (Card card : cards) {
                            if (game.isTopPlaceable(card)) {
                                Card target = game.getTopCardPlaceable(card);
                                move = new Move(Move.MoveType.MOVE);

                                move.setCard(card);
                                move.setStartPosition(card.getPosition(), card.getStackRow());
                                move.setTargetPosition(target.getPosition(), target.getStackRow());
                                move.setPosition(target);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (NotFoundException e) {
            System.out.println("hit exception at moveAceOrTwo strategy");
            e.printStackTrace();
            move = null;
        }
        return move;
    }

}
