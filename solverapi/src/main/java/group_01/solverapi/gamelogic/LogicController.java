package group_01.solverapi.gamelogic;

import group_01.solverapi.model.Game;
import group_01.solverapi.model.Move;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LogicController {
    private StandardStrategy standardStrategy;
    private MoveController moveController;

    public LogicController(StandardStrategy standardStrategy, MoveController moveController) {
        this.standardStrategy = standardStrategy;
        this.moveController = moveController;
    }

    public void makeMove() {
        Move move = standardStrategy.execute();

    }
}
