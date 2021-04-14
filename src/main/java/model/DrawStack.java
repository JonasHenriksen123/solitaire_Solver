package model;

import com.sun.istack.internal.Nullable;

import java.util.LinkedList;

public class DrawStack implements ICardStack {
    private LinkedList<UntCard> cards;

    public DrawStack() {
        //set this for number of cards to be in draw pile at start
        int startCount = 20;
        cards = new LinkedList<UntCard>();
        for (int i = 0; i < startCount; i++) {
            cards.addFirst(new UntCard());
        }
    }

    public void removeTop() {
        cards.removeFirst();
    }

    public void addTop(@Nullable ICard newCard) {
        cards.addFirst(new UntCard());
    }

    public void addTop(ICard[] newCards) {
        for (int i = newCards.length-1; i >= 0; i--) {
            if (newCards[i] instanceof UntCard) {
                cards.addFirst((UntCard) newCards[i]);
            } else {
                cards.addFirst(new UntCard());
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public ICard peekTop() {
        return cards.getFirst();
    }

    public ICard takeTop() {
        removeTop();
        return new UntCard();
    }
}
