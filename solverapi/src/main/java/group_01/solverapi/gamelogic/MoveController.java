package group_01.solverapi.gamelogic;

import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MoveController {
    private Game game;

    public MoveController(Game game) {
        this.game = game;
    }

    public void Execute(Move move) throws ManipulateException {
        switch (move.getType()) {
            case MOVE: {
                Position start = move.getStartPosition();
                Card[] cards = game.takeUntillCard(start.getStackRow(), start.getPosition(), move.getCard());

                Position target = move.getTargetPosition();
                game.placeCards(target.getStackRow(), target.getPosition(), cards);
                break;
            }
            case DRAW: {
                if (game.drawStackEmpty()) {
                    game.reusePlayStack();
                } else {
                    game.drawCards();
                }
                break;
            }
            case TURN: {
                Position position = move.getTurnPosition();
                game.removeCards(position.getStackRow(), position.getPosition(), 1);
                break;
            }
            default: {
                throw new ManipulateException("move had unknown movetype");
            }
        }
    }
}
