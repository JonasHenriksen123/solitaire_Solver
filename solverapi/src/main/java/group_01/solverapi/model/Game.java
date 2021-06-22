package group_01.solverapi.model;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.exceptions.ManipulateException;
import group_01.solverapi.exceptions.NotFoundException;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
        topStacks = new TopStack[] {new TopStack(), new TopStack(), new TopStack(), new TopStack()};
        bottomsStacks = new BottomStack[7];
        for (int i = 0; i < 7; i++) {
            bottomsStacks[i] = new BottomStack(i);
        }
    }

    //region public methods
    public void updateModel(ICardStateDTO cardState) {
        PosHelper pos = new PosHelper(Position.StackRow.PLAY_STACK);
        Card[] card = cardState.getCardsFromStack(pos);
        if (!playStack.peekTop().equals(card[0])) {
            playStack.addTop(card[0]);
        }

        pos.setStackRow(Position.StackRow.TOP_STACKS);
        for (int i = 0; i < 4; i++) {
            pos.setPosition(i);
            Card[] cards = cardState.getCardsFromStack(pos);
            if (cards == null
                    || cards.length == 0
                    || cards.length == 1 && cards[0] == null) {
                continue;
            }
            ICard tempCard = topStacks[i].peekTop();
            if (tempCard == null || ((Card)tempCard).getCardValue() + 1 == cards[0].getCardValue()) {
                topStacks[i].addTop(cards[0]);
            }
        }

        pos.setStackRow(Position.StackRow.BOTTOM_STACKS);
        for (int i = 0; i < 7; i++) {
            pos.setPosition(i);
            Card[] cards = cardState.getCardsFromStack(pos);
            if (cards == null
                    || cards.length == 0
                    || cards.length == 1 && card[0] == null) {
                continue;
            }
            Card[] turnedCards = bottomsStacks[i].getTurnedCards();
            if (cards.length > turnedCards.length) {
                int offset = cards.length - turnedCards.length;

                for (int a = cards.length - 1; a >= 0; a--) {
                    if (a - offset < 0
                        && ((Card)topStacks[i].peekTop()).isBottomPlaceable(cards[a])) {
                        topStacks[i].addTop(cards[a]);
                    }
                    if (!cards[a].equals(turnedCards[a - offset])) {
                        throw new BadInputException("Fatal error when updating model");
                    }
                }
            }
        }
    }

    public void initializeModel(ICardStateDTO cardState) throws InitializeException {
        PosHelper pos = new PosHelper(Position.StackRow.BOTTOM_STACKS);
        for (int i = 0; i < 7; i++) {
            pos.setPosition(i);
            Card[] cards = cardState.getCardsFromStack(pos);
            bottomsStacks[i].addTop(cards[0]);
        }

        pos.setStackRow(Position.StackRow.PLAY_STACK);
        Card[] card = cardState.getCardsFromStack(pos);
        if (card.length > 0) {
            playStack.addTop(new ICard[] {card[0], new UntCard(), new UntCard()});
            drawStack.removeTop(3);
        }
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

        if (playStack.containsCard(13)) {
            return true;
        }

        return false;
    }

    //region drawstack
    public boolean playStackEmpty(){
        return playStack.isEmpty();
    }

    public boolean drawStackEmpty() {
        return drawStack.isEmpty();
    }

    public void reusePlayStack() {
        int count = playStack.size();
        drawStack.addUnturnedCards(count);
    }

    public void drawCards() {
        int count = Math.min(drawStack.size(), 3);
        drawStack.removeTop(count);
        if (count > 1) {
            ICard[] cards = new ICard[count - 1];
            for (int i = 0; i <= count; i++) {
                cards[i] = new UntCard();
            }
            playStack.addTop(cards);
        }
    }
    //endregion

    public int DrawCount() {
        return playStack.size() + drawStack.size();
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

        if (playStack.containsCard(13)) {
            Card king = (Card) playStack.peekTop();
            king.setStackRow(Position.StackRow.PLAY_STACK);
            return king;
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
        if (!playStack.isEmpty() && ((Card) playStack.peekTop()).equals(1)) {
            return true;
        }
        return false;
    }

    public Card getFreeAce() throws NotFoundException{
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            ICard card = stack.peekTop();
            if (card instanceof Card) {
                if (((Card)card).equals(1)){
                    Card card1 = ((Card) card).makeCopy();
                    card1.setPosition(i);
                    card1.setStackRow(Position.StackRow.BOTTOM_STACKS);
                    return card1;
                }
            }
            i++;
        }
        if (!playStack.isEmpty()){
            Card card = ((Card) playStack.peekTop()).makeCopy();
            card.setPosition(0);
            card.setStackRow(Position.StackRow.PLAY_STACK);
            return card;
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

        if (!playStack.isEmpty() && ((Card)playStack.peekTop()).equals(cardValue)) {
            return true;
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

        if(!playStack.isEmpty()) {
            ICard card = playStack.peekTop();
            if (card instanceof Card) {
                if (((Card)card).equals(cardValue)) {
                    Card card1 = (Card) card;
                    card1.setStackRow(Position.StackRow.PLAY_STACK);
                    cards.add(card1);
                }
            }
        }

        if (cards.isEmpty())
            throw new NotFoundException(String.format("No cards with value %o found", cardValue));

        Card[] card = new Card[cards.size()];
        int a = 0;
        for (Card card1: cards) {
            card[a] = card1;
        }
        return card;
    }

    public boolean isTopPlaceable(Card card) {
        for (TopStack stack : topStacks) {
            if (stack.isEmpty())
                continue;
            Card card1 = (Card) stack.peekTop();
            if (card1.isTopPlaceable(card))
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
            if (card1.isBottomPlaceable(card)) {
                card1.setPosition(i);
                card1.setStackRow(Position.StackRow.TOP_STACKS);
                return card1;
            }
            i++;
        }
        throw new NotFoundException("No placeable top position found");
    }

    public boolean hasMoveableStack() {
        for (BottomStack stack : bottomsStacks) {
            if (stack.isTopTurned()) {
                Card bottomCard = stack.getBottomTurnedCard();
                for (BottomStack stack1 : bottomsStacks) {
                    if (stack.equals(stack1) || stack1.isEmpty() || !stack1.isTopTurned())
                    {
                        continue;
                    }
                    Card card = (Card) stack1.peekTop();
                    if (card.isBottomPlaceable(bottomCard)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Card getMostFreeingMoveable() throws NotFoundException{
        int height = 0;
        Card card = null;
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            if (stack.isTopTurned()) {
                Card bottomcard = stack.getBottomTurnedCard();
                for (BottomStack stack1 : bottomsStacks) {
                    if (stack.equals(stack1) || stack1.isEmpty() || !stack1.isTopTurned()){
                        continue;
                    }
                    Card card1 = (Card) stack1.peekTop();
                    if (card1.isBottomPlaceable(bottomcard) && stack.unturnedCards() > height){
                        card = bottomcard.makeCopy();
                        card.setPosition(i);
                        card.setStackRow(Position.StackRow.BOTTOM_STACKS);
                        height = stack.unturnedCards();
                    }
                }
            }
            i++;
        }
        if (card == null) {
            throw new NotFoundException("No placeable cards found");
        }
        return card;
    }

    public int getPlaceableStack(Card card) throws NotFoundException{
        int i = 0;
        for (BottomStack stack : bottomsStacks) {
            if (stack.isTopTurned()){
                Card card1 = (Card) stack.peekTop();
                if (card1.isBottomPlaceable(card)) {
                    return i;
                }
            }
            i++;
        }
        throw new NotFoundException("No placeable stack found for this card");
    }

    public boolean hasTopPlaceableBottomCard() {
        for (BottomStack stack : bottomsStacks) {
           Card card = stack.getBottomTurnedCard();
           if (isTopPlaceable(card)) {
               return true;
           }
        }
        return false;
    }

    public Card[] getTopPlaceableBottomCards() {
        LinkedList<Card> cards = new LinkedList<>();
        for (BottomStack stack : bottomsStacks) {
            Card card = stack.getBottomTurnedCard();
            if (isTopPlaceable(card)) {
                cards.add(card);
            }
        }
        if (cards.isEmpty()) {
            return null;
        }

        Card[] card = new Card[cards.size()];
        int a = 0;
        for (Card card1: cards) {
            card[a] = card1;
        }
        return card;
    }

    public Card[] takeCards(Position.StackRow stackRow, int stackIndex, int amount) {
        ICardStack stack;
        switch (stackRow) {
            case BOTTOM_STACKS: {
                if (stackIndex > 6) {
                    throw new ManipulateException("index out of bounds");
                }
                stack = bottomsStacks[stackIndex];
                break;
            }
            case TOP_STACKS: {
                if (stackIndex > 3) {
                    throw new ManipulateException("index out of bounds");
                }
                stack = topStacks[stackIndex];
                break;
            }
            case PLAY_STACK: {
                if (amount > 1) {
                    throw new ManipulateException("amount out of bounds");
                }
                stack = playStack;
                break;
            }
            default: {
                throw new ManipulateException("Unknown cardstack");
            }
        }

        if (amount > stack.size()) {
            throw new ManipulateException("Trying to remove too many cards");
        }

        return stack.takeTop(amount);
    }

    public Card[] takeUntillCard(Position.StackRow stackRow, int stackIndex, Card card) {
        ICardStack stack;
        switch (stackRow) {
            case BOTTOM_STACKS: {
                if (stackIndex > 6) {
                    throw new ManipulateException("index out of bounds");
                }
                stack = bottomsStacks[stackIndex];
                break;
            }
            case TOP_STACKS: {
                if (stackIndex > 3) {
                    throw new ManipulateException("index out of bounds");
                }
                stack = topStacks[stackIndex];
                break;
            }
            case PLAY_STACK: {
                stack = playStack;

                break;
            }
            default: {
                throw new ManipulateException("Unknown cardstack");
            }
        }

        if (stack.isEmpty()) {
            throw new ManipulateException("No cards in stack");
        }

        if (!stack.containsCard(card)) {
            throw new ManipulateException("card not founf in stack");
        }

        return stack.takeTop(card);
    }

    public void placeCards(Position.StackRow stackRow, int stackIndex, Card[] cards) {
        ICardStack stack;
        switch (stackRow) {
            case BOTTOM_STACKS: {
                if (stackIndex > 6) {
                    throw new ManipulateException("Index out of bounds");
                }
                stack = bottomsStacks[stackIndex];
                break;
            }
            case TOP_STACKS: {
                if (cards.length > 1) {
                    throw new ManipulateException("Can only move one card");
                }
                if (stackIndex > 3) {
                    throw new ManipulateException("Index out of bounds");
                }
                stack = topStacks[stackIndex];
                break;
            }
            default: {
                throw new ManipulateException("Unknown cardstack");
            }
        }

        stack.addTop(cards);
    }
    //endregion

    //region private methods
    private class PosHelper extends Position {
        public PosHelper(StackRow stackRow) {
            this.setStackRow(stackRow);
        }
    }
    //endregion
}
