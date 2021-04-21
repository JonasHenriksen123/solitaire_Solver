package group_01.solverapi.picrecaccess;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CardPlacementDAO implements ICardPlacementDAO {
    public CardStateDTO getInitialGameState() throws IOException {
        return null;
    }

    public CardStateDTO getCurrentGameState() throws IOException {
        return null;
    }
}
