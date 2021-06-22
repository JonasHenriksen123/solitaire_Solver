package group_01.solverapi.model;

import group_01.solverapi.exceptions.BadInputException;

import java.rmi.NotBoundException;

public class Card extends Position implements ICard {
    private int cardValue;
    private Suit suit;

    //region overrides
    public boolean isTurned() {
        return true;
    }

    //endregion

    public enum Suit {
        HEARTS,
        SPADES,
        DIAMOND,
        CLUB
    }

    public Card(int cardValue, Suit suit) {
        setCardValue(cardValue);
        this.suit = suit;
    }

    public boolean isBottomPlaceable(Card other) {
        if (other.cardValue != this.cardValue -1) {
            return false;
        }
        if (other.suit == Suit.HEARTS || other.suit == Suit.DIAMOND) {
            return this.suit == Suit.SPADES || this.suit == Suit.CLUB;
        } else {
            return this.suit == Suit.HEARTS || this.suit == Suit.DIAMOND;
        }
    }

    public boolean isTopPlaceable(Card other) {
        if (other.cardValue != this.cardValue + 1) {
            return false;
        }
        return this.suit == other.suit;
    }

    public boolean equals(Card card) {
        return this.suit == card.suit && this.cardValue == card.cardValue;
    }

    public boolean equals(int cardValue) {
        return this.cardValue == cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setCardValue(int cardValue) throws BadInputException {
        if(cardValue < 1 || cardValue > 13){
            throw new BadInputException("value is not within acceptable practice");
        } else{
            this.cardValue = cardValue;
        }
    }

    public int getCardValue() {return cardValue; }

    public static Card toCard(String card) throws BadInputException {
        Suit suit;
        int cardValue = 0;
        int suitPlacement = 0;

        if (card.length() == 3) {
            cardValue = 10;
            suitPlacement = 2;
        } else {

            if (card.charAt(0) > '1' && card.charAt(0) <= '9') {
                cardValue = card.charAt(0) - 48;
            } else {
                switch (card.charAt(0)) {
                    case 'A': {
                        cardValue = 1;
                        break;
                    }
                    case 'K': {
                        cardValue = 13;
                        break;
                    }
                    case 'Q': {
                        cardValue = 12;
                        break;
                    }
                    case 'J': {
                        cardValue = 11;
                        break;
                    }
                    default: {
                        throw new BadInputException("card face value was unknown");
                    }
                }
            }
            suitPlacement = 1;
        }

        switch (card.charAt(suitPlacement)) {
            case 'h': {
                suit = Suit.HEARTS;
                break;
            }
            case 's': {
                suit = Suit.SPADES;
                break;
            }
            case 'c': {
                suit = Suit.CLUB;
                break;
            }
            case 'd': {
                suit = Suit.DIAMOND;
                break;
            }
            default:
                throw new BadInputException("Suit of card was unknown");
        }


        return new Card(cardValue, suit);

    }

    public Card makeCopy() {
        return new Card(this.cardValue, this.suit);
    }

    @Override
    public String toString() {
        String val, suit;
        switch (this.cardValue) {
            case 1: {
                val = "Ace";
                break;
            }
            case 11: {
                val = "Jack";
                break;
            }
            case 12: {
                val = "Queen";
                break;
            }
            case 13: {
                val = "King";
                break;
            }
            default: {
                val = String.valueOf(cardValue);
            }
        }
        switch (this.suit) {
            case SPADES: {
                suit = "Spades";
                break;
            }
            case HEARTS: {
                suit = "Hearts";
                break;
            }
            case DIAMOND: {
                suit = "Diamonds";
                break;
            }
            case CLUB: {
                suit = "Clubs";
                break;
            }
            default: {
                return null;
            }
        }

        return String.format("%s of %s", val, suit);
    }
}
