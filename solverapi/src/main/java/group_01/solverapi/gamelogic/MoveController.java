package group_01.solverapi.gamelogic;

import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
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
                //TODO execute move
            }
            case KING_MOVE: {
                //TODO execute king move
            }
            case DRAW: {
                //TODO execute draw move
            }
            case TURN: {
                //TODO execute turn move
            }
            case TOP_STACK_MOVE: {
                //TODO execute topstackmove
            }
            default: {
                throw new ManipulateException("move had no known movetype");
            }
        }
    }
}
