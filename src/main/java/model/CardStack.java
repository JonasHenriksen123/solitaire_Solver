package model;

import java.util.LinkedList;

public class CardStack {
    private LinkedList<ICard> cards;


    public CardStack(int unturnedCards) {
        cards = new LinkedList<ICard>();
        for (int i = 0; i < unturnedCards; i++) {
            cards.add(new UntCard());
        }
    }

    public boolean isTopTurned() {
        return cards.getFirst().isTurned();
    }

    public void removeTop() {
        cards.removeFirst();
    }

    public void addTop(Card newCard) {
        cards.addFirst(newCard);
    }

    public void addTop(Card[] newCards) {
        for (int i = newCards.length-1; i >= 0; i--) {
            cards.addFirst(newCards[i]);
        }
    }

    public int stackSize() {
        return cards.size();
    }


}
