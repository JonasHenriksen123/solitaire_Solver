package group_01.solverapi.model;

import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.exceptions.NotFoundException;
import java.util.LinkedList;

public class BottomStack implements ICardStack {
    private LinkedList<ICard> cards;

    public BottomStack(int unturnedCards) {
        cards = new LinkedList<ICard>();
        for (int i = 0; i < unturnedCards; i++) {
            cards.add(new UntCard());
        }
    }



    //region overrides
    public void removeTop() throws ManipulateException {
        cards.removeFirst();
    }

    public void addTop(ICard newCard) throws ManipulateException {
        if (newCard instanceof Card)
            cards.addFirst(newCard);
        else
            throw new ManipulateException("Tried to add unturned card to top of stack");
    }

    public void addTop(ICard[] newCards) throws ManipulateException {
        for (int i = newCards.length-1; i >= 0; i--) {
            if (newCards[i] instanceof Card) {
                cards.addFirst(newCards[i]);
            } else {
                throw new ManipulateException("Tried to add unturned card to top of stack");
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public ICard peekTop() throws NotFoundException {
        if (cards.isEmpty())
            throw new NotFoundException("Card stack is empty");
        return cards.getFirst();
    }

    public ICard takeTop() throws ManipulateException {
        ICard card = cards.getFirst();
        cards.removeFirst();
        return card;
    }

    @Override
    public boolean isEmpty() {
        return size() ==0;
    }

    @Override
    public boolean containsCard(Card card) {
        for(ICard card1: this.cards){
            if(card1 instanceof Card){
                if (((Card) card1).equals(card))
                    return true;

            }
        }
        return false;
    }

    @Override
    public boolean containsCard(int cardValue){
        for(ICard card1: this.cards){
            if(card1 instanceof Card){
                if (((Card) card1).equals(cardValue))
                    return true;
            }
        }
        return false;
    }

    @Override
    public Card[] takeTop(int amount) throws ManipulateException {
        if (amount > size()) {
            throw new ManipulateException("amount too large");
        }
        Card[] cards = new Card[amount];
        for (int i = 0; i < amount; i++) {
            cards[i] = (Card) this.cards.removeFirst();
        }
        return cards;
    }

    @Override
    public Card[] takeTop(Card card) throws ManipulateException {
        int count = 1;
        for (ICard card1 : cards) {
            if (card1 instanceof Card) {
                if (((Card) card1).equals(card)) {
                    break;
                }
            }
            count++;
        }
        return takeTop(count);
    }


    //endregion



    public boolean isTopTurned() {
        return cards.getFirst().isTurned();
    }

    public int unturnedCards() {
        int numb = 0;
        for (ICard card : cards) {
            if (card instanceof UntCard)
                numb++;
        }
        return numb;
    }

    public int turnedCards() {
        return size() - unturnedCards();
    }

    public Card getCard(int cardValue) throws NotFoundException {
        for (ICard card : this.cards) {
            if (card instanceof  Card) {
                if (((Card) card).equals(cardValue))
                    return (Card) card;
            }
        }
        throw new NotFoundException("No turned card with this value");
    }

    public Card getBottomTurnedCard() throws NotFoundException {
        Card bottomCard = null;
        for (ICard card : this.cards) {
            if (card.isTurned()) {
                bottomCard = (Card) card;
            }
        }
        if (bottomCard == null) {
            throw new NotFoundException("No turned cards in this stack");
        }
        return bottomCard;
    }

    public Card[] takeUntillCard(Card card) throws ManipulateException {
        int i = 1;
        LinkedList<Card> cards = new LinkedList<Card>();
        for (ICard card1 : this.cards) {
            if (card1 instanceof UntCard) {
                throw new ManipulateException("The stack did not contain the given card");
            }
            cards.add((Card) card1);
            if (card1.equals(card)) {
                break;
            }
            i++;
        }

        while (i > 0) {
            this.cards.removeFirst();
            i--;
        }

        return (Card[]) cards.toArray();
    }

    public Card[] getTurnedCards() {
        if (turnedCards() == 0)
        {
            return null;
        }
        LinkedList<Card> cards = new LinkedList<>();
        for (ICard card : this.cards) {
            if (card instanceof Card) {
                cards.add((Card) card);
            } else {
                break;
            }
        }
        return (Card[]) cards.toArray();
    }

}
