package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void isPlaceableTest() {
        try {
            Card moveCard = new Card(1, Card.Suit.DIAMOND), staticCard = new Card(2, Card.Suit.SPADES);

            Assertions.assertTrue(staticCard.isPlaceable(moveCard));

            //value test
            moveCard = new Card(8, Card.Suit.DIAMOND);
            staticCard = new Card(7, Card.Suit.SPADES);

            Assertions.assertFalse(staticCard.isPlaceable(moveCard));

            //suit test
            moveCard = new Card(1, Card.Suit.DIAMOND);
            staticCard = new Card(4, Card.Suit.DIAMOND);

            Assertions.assertFalse(staticCard.isPlaceable(moveCard));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
