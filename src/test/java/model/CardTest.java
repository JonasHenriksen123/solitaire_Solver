package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void isPlaceableTest() {
        Card moveCard = new Card(), staticCard = new Card();
        moveCard.setSuit(Card.Suit.DIAMOND);
        staticCard.setSuit(Card.Suit.SPADES);

        Assertions.assertTrue(staticCard.isPlaceable(moveCard));

        moveCard = new Card();
        staticCard = new Card();

        moveCard.setSuit(Card.Suit.SPADES);
        staticCard.setSuit(Card.Suit.CLUB);

        Assertions.assertFalse(staticCard.isPlaceable(moveCard));

    }
}
