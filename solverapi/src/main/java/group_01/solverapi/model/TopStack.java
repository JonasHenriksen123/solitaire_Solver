package group_01.solverapi.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class TopStack implements ICardStack {
    private LinkedList<Card> cards;

    public TopStack() {
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
            throw new Exception("Tried to add unturned card to top of stack");
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
        //TODO implement
        return false;
    }

    //endregion


}
