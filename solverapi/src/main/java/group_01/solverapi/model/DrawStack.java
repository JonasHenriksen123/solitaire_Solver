package group_01.solverapi.model;

import com.sun.istack.internal.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    //region overrides
    public void removeTop() throws ManipulateException {
        cards.removeFirst();
    }

    public void addTop(@Nullable ICard newCard) throws ManipulateException {
        cards.addFirst(new UntCard());
    }

    public void addTop(ICard[] newCards) throws ManipulateException {
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

    public ICard takeTop() throws ManipulateException {
        removeTop();
        return new UntCard();
    }

    @Override
    public boolean isEmpty() {
        return size() ==0;
    }

    @Override
    public boolean containsCard(Card card) {
        throw new NotImplementedException();
    }

    //endregion
}
