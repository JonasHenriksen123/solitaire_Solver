package group_01.solverapi.picrecaccess;

import group_01.solverapi.model.Card;
import group_01.solverapi.model.Position;

public interface ICardStateDTO {
    Card[] getCardsFromStack(Position pos);
    ICardStateDTO initCardState(String input);
}
