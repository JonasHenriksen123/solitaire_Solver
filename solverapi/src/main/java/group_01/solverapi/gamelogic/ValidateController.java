package group_01.solverapi.gamelogic;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.model.Card;
import group_01.solverapi.model.Game;
import group_01.solverapi.model.Position;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidateController {
    private Game game;

    public ValidateController(Game game) {
        this.game = game;
    }

    public void validate(ICardStateDTO cardState) throws BadInputException {
        //this part is not ready
    }

    public void validateinit(ICardStateDTO cardState) throws InitializeException {
        PosHelper pos = new PosHelper(Position.StackRow.BOTTOM_STACKS);
        for (int i = 0; i < 7; i++) {
            pos.setPosition(i);
            if (cardState.getCardsFromStack(pos).length != 1) {
                throw new InitializeException();
            }
        }

        pos.setStackRow(Position.StackRow.TOP_STACKS);
        for (int i = 0; i < 4; i++) {
            pos.setPosition(i);
            Card[] cards = cardState.getCardsFromStack(pos);
            if (cards.length != 0) {
                if (cards.length == 1 && cards[0] == null) {
                    continue;
                }
                throw new InitializeException();
            }
        }
    }

    private class PosHelper extends Position {
        public PosHelper(StackRow stackRow) {
            this.setStackRow(stackRow);
        }
    }
}
