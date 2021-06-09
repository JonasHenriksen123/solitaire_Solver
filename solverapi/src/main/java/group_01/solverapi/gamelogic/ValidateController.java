package group_01.solverapi.gamelogic;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.model.Game;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidateController {
    private Game game;

    public ValidateController(Game game) {

        this.game = game;
    }

    public void validate(ICardStateDTO cardState) throws BadInputException {
        //TODO validate that applying changes wont destoy models integrity
    }

    public void validateinit(ICardStateDTO cardState) throws InitializeException {
        //TODO validatet that the cardstate is ok
    }
}
