package group_01.solverapi.model;

import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.exceptions.NotFoundException;

public interface ICardStack {
    public void removeTop() throws ManipulateException;
    public void addTop(ICard newCard) throws ManipulateException;
    public void addTop(ICard[] newCards) throws ManipulateException;
    public int size();
    public ICard peekTop() throws NotFoundException;
    public ICard takeTop() throws ManipulateException;
    public Card[] takeTop(int amount) throws ManipulateException;
    public Card[] takeTop(Card card) throws ManipulateException;
    public boolean isEmpty();
    public boolean containsCard(Card card);
    public boolean containsCard(int value);
}
