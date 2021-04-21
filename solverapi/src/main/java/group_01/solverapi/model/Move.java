package group_01.solverapi.model;

public class Move {

    private Card card;
    private Card position;
    private MoveType type;

    public enum MoveType {
        MOVE, KING_MOVE, TURN, TOP_STACK_MOVE, DRAW
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setPosition(Card position) {
        this.position = position;
    }

    public Move(MoveType type) {
        this.type = type;
    }

    public String toString() {
        switch (type) {
            case MOVE:
                return "Ryk " + card.toString() + " til " + position.toString() + ".";
            case KING_MOVE:
                return "Ryk " + card.toString() + " til en tom række.";
            case TOP_STACK_MOVE:
                return "Ryk " + card.toString() + " til top stakken.";
            case DRAW:
                return "Træk et kort fra bunke.";
            case TURN:
                return "Vend et kort.";
            default:
                return "Kan ikke finde på træk.";
        }
    }
}
