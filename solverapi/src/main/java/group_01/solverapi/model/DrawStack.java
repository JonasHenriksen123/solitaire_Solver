package group_01.solverapi.model;

import com.sun.istack.internal.Nullable;
import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.exceptions.NotFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class DrawStack implements ICardStack {
    private LinkedList<UntCard> cards;

    public DrawStack() {
        int startCount = 24;
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

    public ICard peekTop() throws NotFoundException {
        if (cards.isEmpty())
            throw new NotFoundException("Card stack is empty");
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

    @Override
    public boolean containsCard(int value){throw new NotImplementedException();}

    @Override
    public Card[] takeTop(int amount) throws ManipulateException {
        throw new NotImplementedException();
    }

    @Override
    public Card[] takeTop(Card card) throws ManipulateException {
        throw new NotImplementedException();
    }

    //endregion

    //region public methods
    public void removeTop(int count) throws ManipulateException {
        if (count > size()) {
            throw new ManipulateException("attempted to move more cards than present");
        }
        for (int i = 0; i < count; i++) {
            cards.removeFirst();
        }
    }

    public void addUnturnedCards(int count) throws ManipulateException {
        if (!isEmpty()) {
            throw new ManipulateException("tried to reset drawstck when not empty");
        }
        for (int i = 0; i < count; i++) {
            addTop(new UntCard());
        }
    }
    //endregion
}
