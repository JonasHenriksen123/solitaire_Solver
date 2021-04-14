package model;

import java.util.LinkedList;

public class PlayStack implements ICardStack {
    private LinkedList<Card> cards;

    public PlayStack(){
        cards = new LinkedList<Card>();
    }

    //region overrides
    public void removeTop() {
        cards.removeFirst();
    }

    public void addTop(ICard newCard) throws Exception {
        if (newCard instanceof Card) {
            cards.addFirst((Card) newCard);
        } else {
            throw new Exception("Tried to add unturned card to stop of stack");
        }
    }

    public void addTop(ICard[] newCards) throws Exception {
        for (int i = newCards.length-1; i >= 0; i--) {
            if (newCards[i] instanceof Card) {
                cards.addFirst((Card) newCards[i]);
            } else {
                throw new Exception("Tried to add unturned card to top of stack");
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
        Card card = cards.getFirst();
        cards.removeFirst();
        return card;
    }
    //endregion


}
