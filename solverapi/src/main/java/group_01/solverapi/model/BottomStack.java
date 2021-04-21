package group_01.solverapi.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
    public void removeTop() {
        cards.removeFirst();
    }

    public void addTop(ICard newCard) throws Exception {
        if (newCard instanceof Card)
            cards.addFirst(newCard);
        else
            throw new Exception("Tried to add unturned card to top of stack");
    }

    public void addTop(ICard[] newCards) throws Exception {
        for (int i = newCards.length-1; i >= 0; i--) {
            if (newCards[i] instanceof Card) {
                cards.addFirst(newCards[i]);
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

    //endregion

    public boolean containsCard(int cardValue){
        for(ICard card1: this.cards){
            if(card1 instanceof Card){
                if (((Card) card1).equals(cardValue))
                    return true;
            }
        }
        return false;
    }

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
