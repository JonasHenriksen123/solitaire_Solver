package group_01.solverapi.picrecaccess;

import group_01.solverapi.model.Card;
import group_01.solverapi.model.Position;
import org.json.simple.parser.ParseException;

public interface ICardStateDTO {
    Card[] getCardsFromStack(Position pos);
    void initCardState(String input) throws ParseException;
}
