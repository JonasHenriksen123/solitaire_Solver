package group_01.solverapi.model;

import group_01.solverapi.exceptions.InitializeException;
import group_01.solverapi.picrecaccess.CardStateDTO;
import group_01.solverapi.picrecaccess.ICardStateDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

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

    public boolean getFreeKing(){
        for(BottomStack stack: bottomsStacks){
            if(stack.containsCard(13) && stack.unturnedCards() == 0)
                return true;
        }
        return false;
    }

    public boolean playStackEmpty(){
        return playStack.isEmpty();
    }

    public @Nullable
    ICard getCard(){
        return null;
    }

}
