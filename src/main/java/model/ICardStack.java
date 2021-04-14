package model;

import java.util.LinkedList;

public interface ICardStack {
    public void removeTop();
    public void addTop(ICard newCard) throws Exception;
    public void addTop(ICard[] newCards) throws Exception;
    public int size();
    public ICard peekTop();
    public ICard takeTop();

}
