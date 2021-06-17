package group_01.solverapi.picrecaccess;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.model.Card;
import group_01.solverapi.model.Position;
import org.springframework.lang.NonNull;

public class CardStateDTO implements ICardStateDTO {
    private Card playStack;
    private Card[][] topStacks;
    private Card[][] bottomStacks;

    public CardStateDTO(String input) {
       initCardState(input);
    }

    @Override
    public ICardStateDTO initCardState(@NonNull String input) {
        return new CardStateDTO(input);
    }

    @Override
    public Card[] getCardsFromStack(@NonNull Position pos) throws BadInputException {
        switch (pos.getStackRow()) {
            case PLAY_STACK: {
                return new Card[] {playStack};
            }
            case TOP_STACKS: {
                int p = pos.getPosition();
                if (p > 3 || p < 0)
                    throw new BadInputException(String.format("Trying to get stack %s from top stacks failed", p));
                return topStacks[p];
            }
            case BOTTOM_STACKS: {
                int p = pos.getPosition();
                if (p > 6 || p < 0)
                    throw new BadInputException(String.format("Trying to get stack %s from bottom stacks failed", p));
                return bottomStacks[p];
            }
            default: {
                throw new BadInputException(String.format("Trying to get unknown stack: %s failed", pos.getStackRow().toString()));
            }
        }
    }
}
