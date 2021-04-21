package group_01.solverapi.picrecaccess;

import java.io.IOException;

public interface ICardPlacementDAO {
    public CardStateDTO getInitialGameState() throws IOException;
    public CardStateDTO getCurrentGameState() throws IOException;
}