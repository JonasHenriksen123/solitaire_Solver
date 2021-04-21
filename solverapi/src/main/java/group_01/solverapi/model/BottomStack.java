package group_01.solverapi.model;

import group_01.solverapi.exceptions.ManipulateException;

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

    public ICard peekTop() {
        return cards.getFirst();
    }

    public ICard takeTop() throws ManipulateException {
        ICard card = cards.getFirst();
        cards.removeFirst();
        return card;
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
}
