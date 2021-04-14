package model;

public class Card implements ICard
{
    private int cardValue;
    private Suit suit;

    public boolean isTurned() {
        return true;
    }

    public enum Suit {
        HEARTS,
        SPADES,
        DIAMOND,
        CLUB
    }

    public boolean isPlaceable(Card other) {
        if (other.suit == Suit.HEARTS || other.suit == Suit.DIAMOND) {
            return this.suit == Suit.SPADES || this.suit == Suit.CLUB;
        } else {
            return this.suit == Suit.HEARTS || this.suit == Suit.DIAMOND;
        }
    }


    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
