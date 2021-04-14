package model;

import picrecaccess.CardStateDTO;

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

    public void updateModel(CardStateDTO cardState) throws Exception {

    }

}
