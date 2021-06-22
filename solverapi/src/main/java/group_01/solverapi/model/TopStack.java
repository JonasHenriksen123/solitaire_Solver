package group_01.solverapi.model;

import group_01.solverapi.exceptions.NotFoundException;
import group_01.solverapi.exceptions.ManipulateException;

import java.util.LinkedList;

public class TopStack implements ICardStack {
    private LinkedList<Card> cards;

    public TopStack() {
        cards = new LinkedList<Card>();
    }

    //region overrides
    public void removeTop() throws ManipulateException {
        cards.removeFirst();
    }

    @Override
    public void removeTop(int amount) throws ManipulateException {
        if (amount > size()) {
            throw new ManipulateException("amount too large");
        }
        for (int i = 0; i < amount; i++) {
           this.cards.removeFirst();
        }
    }

    public void addTop(ICard newCard) throws ManipulateException {
        if (newCard instanceof Card) {
            cards.addFirst((Card) newCard);
        } else {
            throw new ManipulateException("Tried to add unturned card to top of stack");
        }
    }

    public void addTop(ICard[] newCards) throws ManipulateException {
        for (int i = newCards.length - 1; i >= 0; i--) {
            if (newCards[i] instanceof Card) {
                cards.addFirst((Card) newCards[i]);
            } else {
                throw new ManipulateException("Tried to add unturned card to top of stack");
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public ICard peekTop() {
        return cards.getFirst();
    }

    public ICard takeTop() throws ManipulateException {
        Card card = cards.getFirst();
        cards.removeFirst();
        return card;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsCard(Card card) {
        for (Card card1 : cards) {
            if (card1.equals(card)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsCard(int cardValue) {
        for (Card card : cards) {
            if (card.equals(cardValue)) {
                return true;
            }
        }
        return false;
    }

    public Card[] takeTop(int amount) throws ManipulateException {
        if (amount > size()) {
            throw new ManipulateException("amount too large");
        }
        Card[] cards = new Card[amount];
        for (int i = 0; i < amount; i++) {
            cards[i] = this.cards.removeFirst();
        }
        return cards;
    }

    @Override
    public Card[] takeTop(Card card) throws ManipulateException {
        Card card1 = cards.peekFirst();
        if (card1 == null) {
            throw new ManipulateException("No cards in stack");
        }

        if (card1.equals(card)) {
            return new Card[] { cards.removeFirst()};
        }

        throw new ManipulateException("Can only remove one card from playstack");
    }
    //endregion


    public Card getCard(int cardValue) throws NotFoundException {
        Card card = cards.peekFirst();
        if (card == null) {
            throw new ManipulateException("No cards in stack");
        }

        if (card.equals(cardValue)) {
            return card;
        }
        throw new NotFoundException(String.format("No card with value %d", cardValue));
    }

}
