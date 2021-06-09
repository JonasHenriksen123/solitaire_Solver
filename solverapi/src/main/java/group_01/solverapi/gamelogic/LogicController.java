package group_01.solverapi.gamelogic;

import group_01.solverapi.model.Move;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LogicController {
    private StandardStrategy standardStrategy;
    private MoveController moveController;
    private DoSomethingStrategy doSomethingStrategy;

    public LogicController(StandardStrategy standardStrategy, MoveController moveController, DoSomethingStrategy doSomethingStrategy) {
        this.standardStrategy = standardStrategy;
        this.moveController = moveController;
        this.doSomethingStrategy = doSomethingStrategy;
    }

    public Move makeMove() {
        Move move = standardStrategy.execute();

        if (move == null) {
            move = doSomethingStrategy.execute();
        }

        if (move == null) {
            //we lost bad luck
            return null;
        }

        moveController.Execute(move);

        return move;
    }
}
