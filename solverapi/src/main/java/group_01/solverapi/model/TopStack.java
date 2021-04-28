package group_01.solverapi.model;

import group_01.solverapi.exceptions.NotFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import group_01.solverapi.exceptions.ManipulateException;
import org.springframework.lang.Nullable;

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

    public void addTop(ICard newCard) throws ManipulateException {
        if (newCard instanceof Card) {
            cards.addFirst((Card) newCard);
        } else {
            throw new ManipulateException("Tried to add unturned card to top of stack");
        }
    }

    public void addTop(ICard[] newCards) throws ManipulateException {
        for (int i = newCards.length-1; i >= 0; i--) {
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
        Card card =  cards.getFirst();
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

    //endregion

    public boolean containsCard(int cardValue) {
        for (Card card : cards) {
            if (card.equals(cardValue)) {
                return true;
            }
        }
        return false;
    }

    public Card getCard(int cardValue) throws NotFoundException {
        for (Card card : cards) {
            if (card.equals(cardValue))
                return card;
        }
        throw new NotFoundException(String.format("No card with value %d", cardValue));
    }

}
