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

    public Card(int cardValue, Suit suit) throws Exception {
        setCardValue(cardValue);
        this.suit = suit;
    }

    public boolean isPlaceable(Card other) {
        if (other.cardValue != this.cardValue -1) {
            return false;
        }
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

    public void setCardValue(int cardValue) throws Exception {
        if(cardValue < 1 || cardValue > 13){
            throw new Exception("value is not within acceptable practice");
        } else{
            this.cardValue = cardValue;
        }
    }

    public int getCardValue() {return cardValue; }

}
