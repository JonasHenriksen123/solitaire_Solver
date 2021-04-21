package group_01.solverapi.model;

import group_01.solverapi.exceptions.ManipulateException;

import java.util.LinkedList;

public interface ICardStack {
    public void removeTop() throws ManipulateException;
    public void addTop(ICard newCard) throws ManipulateException;
    public void addTop(ICard[] newCards) throws ManipulateException;
    public int size();
    public ICard peekTop();
    public ICard takeTop() throws ManipulateException;
}
