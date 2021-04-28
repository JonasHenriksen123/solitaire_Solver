package group_01.solverapi.model;

import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.exceptions.NotFoundException;
import group_01.solverapi.picrecaccess.CardStateDTO;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.LinkedList;

@Component
@Primary
public class Game {
    private DrawStack drawStack;
    private PlayStack playStack;
    private TopStack[] topStacks;
    private BottomStack[] bottomsStacks;

    public Game() {
        drawStack = new DrawStack();
        playStack = new PlayStack();
        topStacks = new TopStack[4];
        for (TopStack stack: topStacks) {
            stack = new TopStack();
        }
        bottomsStacks = new BottomStack[7];
        for (int i = 0; i < 7; i++) {
            bottomsStacks[i] = new BottomStack(i+1);
        }
    }

    public void updateModel(ICardStateDTO cardState)  {
        //TODO update model by cardstate object
    }

    public void initializeModel(ICardStateDTO cardState) throws InitializeException {
        //TODO update model by cardstate object
    }

    public boolean hasEmptyBottomStack() {
        for(ICardStack stack: bottomsStacks) {
            if(stack.isEmpty())
                return true;
        }
        return false;
    }

    public boolean hasFreeKing(){
        for(BottomStack stack: bottomsStacks){
            if(stack.containsCard(13) && stack.unturnedCards() != 0)
                return true;
        }
        for (TopStack stack : topStacks) {
            if (stack.containsCard(13))
                return true;
        }
        return false;
    }

    public boolean playStackEmpty(){
        return playStack.isEmpty();
    }

    public Card getFreeKing() throws NotFoundException {
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            if (stack.containsCard(13) && stack.unturnedCards() != 0) {
                Card king = stack.getCard(13);
                king.setPosition(i);
                king.setStackRow(Position.StackRow.BOTTOM_STACKS);
                return king;
            }
            i++;
        }
        for (TopStack stack : topStacks) {
            if (stack.containsCard(13)) {
                Card king = stack.getCard(13);
                king.setPosition(i);
                king.setStackRow(Position.StackRow.TOP_STACKS);
                return king;
            }
            i++;
        }
        throw new NotFoundException("No free king found");
    }

    public int getEmptyBottomStack() throws NotFoundException {
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            if (stack.isEmpty()) {
                return i;
            }
            i++;
        }
        throw new NotFoundException("No empty stack found");
    }

    public boolean hasUnturnedBottomStack() {
        for (ICardStack stack : bottomsStacks) {
            if (stack.isEmpty())
                continue;
            if (!stack.peekTop().isTurned()) {
                return true;
            }
        }
        return false;
    }

    public int getUnturnedBottomStack() throws NotFoundException {
        int i = 0;
        for (ICardStack stack : bottomsStacks) {
            if (stack.isEmpty()) {
                i++;
                continue;
            }
            if (!stack.peekTop().isTurned()){
                return i;
            }
            i++;
        }
        throw new NotFoundException("No unturned top cards found");
    }

    public boolean hasFreeAce() {
        for (BottomStack stack : bottomsStacks) {
            if (stack.isEmpty())
                continue;
            ICard card = stack.peekTop();
            if (card instanceof Card) {
                if (((Card) card).equals(1))
                    return true;
            }
        }
        return false;
    }

    public Card getFreeAce() throws NotFoundException {
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            ICard card = stack.peekTop();
            if (card instanceof Card) {
                if (((Card)card).equals(1)){
                    Card card1 = (Card) card;
                    card1.setPosition(i);
                    card1.setStackRow(Position.StackRow.BOTTOM_STACKS);
                    return card1;
                }
            }
            i++;
        }
        throw new NotFoundException("No free ace found");
    }

    public int getEmptyTopStack() throws NotFoundException {
        int i = 0;
        for (TopStack stack : topStacks) {
            if (stack.isEmpty()) {
                return i;
            }
            i++;
        }
        throw new NotFoundException("No empty topstacks found");
    }

    public boolean hasFreeCard(int cardValue) {
        for (BottomStack stack : bottomsStacks) {
            if (stack.isEmpty())
                continue;
            ICard card = stack.peekTop();
            if (card instanceof Card) {
                if (((Card) card).equals(cardValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Card[] getFreeCards(int cardValue) throws NotFoundException {
        LinkedList<Card> cards = new LinkedList<Card>();

        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            if (stack.isEmpty()) {
                i++;
                continue;
            }
            ICard card = stack.peekTop();
            if (card instanceof Card) {
                if (((Card) card).equals(cardValue)) {
                    Card card1 = (Card) card;
                    card1.setPosition(i);
                    card1.setStackRow(Position.StackRow.BOTTOM_STACKS);
                    cards.add(card1);
                }
            }
            i++;
        }

        if (cards.isEmpty())
            throw new NotFoundException(String.format("No cards with value %o found", cardValue));

        return (Card[]) cards.toArray();
    }

    public boolean isTopPlaceable(Card card) {
        for (TopStack stack : topStacks) {
            if (stack.isEmpty())
                continue;
            Card card1 = (Card) stack.peekTop();
            if (card1.isPlaceable(card))
                return true;
        }
        return false;
    }

    public Card getTopCardPlaceable(Card card) throws NotFoundException {
        int i = 0;
        for (TopStack stack : topStacks) {
            if (stack.isEmpty()) {
                i++;
                continue;
            }
            Card card1 = (Card) stack.peekTop();
            if (card1.isPlaceable(card)) {
                card1.setPosition(i);
                card1.setStackRow(Position.StackRow.TOP_STACKS);
                return card1;
            }
            i++;
        }
        throw new NotFoundException("No placeable top position found");
    }

}
