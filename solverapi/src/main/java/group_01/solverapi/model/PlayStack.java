package group_01.solverapi.model;

import group_01.solverapi.exceptions.NotFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import group_01.solverapi.exceptions.ManipulateException;
import java.util.LinkedList;

public class PlayStack implements ICardStack {
    private LinkedList<ICard> cards;

    public PlayStack(){
        cards = new LinkedList<ICard>();
    }

    //region overrides
    public void removeTop() throws ManipulateException {
        cards.removeFirst();
    }

    public void addTop(ICard newCard) throws ManipulateException {
        if (newCard instanceof Card) {
            cards.addFirst((Card) newCard);
        } else {
            throw new ManipulateException("Tried to add unturned card to stop of stack");
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

    public ICard peekTop() throws NotFoundException {
        if (cards.isEmpty())
            throw new NotFoundException("Card stack is empty");
        return cards.getFirst();
    }

    public ICard takeTop() throws ManipulateException {
        if (cards.isEmpty()) {
            throw new ManipulateException("Card stack is empty");
        }
        return cards.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return size() ==0;
    }

    @Override
    public boolean containsCard(Card card) {
        return this.cards.getFirst().equals(card);
    }

    @Override
    public boolean containsCard(int value) {
        return this.cards.getFirst().equals(value);
    }

    public Card[] takeTop(int amount) throws ManipulateException {
        if (amount > 1) {
            throw new ManipulateException("amount too large");
        }
        if (cards.isEmpty()) {
            throw new ManipulateException("stack was empty");
        }

        ICard card1 = cards.peekFirst();
        if (card1 instanceof Card) {
            return new Card[] { (Card) cards.removeFirst()};
        }
        throw new ManipulateException("top card was unturned...?");
    }

    @Override
    public Card[] takeTop(Card card) throws ManipulateException {
        ICard card1 = cards.peekFirst();
        if (card1 instanceof Card) {
            if (card.equals((Card)card1)) {
                return new Card[] { (Card) cards.removeFirst()};
            }
        }

        throw new ManipulateException("Can only remove one card from playstack");
    }

    //endregion


}
