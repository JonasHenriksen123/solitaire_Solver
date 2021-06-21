package group_01.solverapi.model;

public class Move {

    private Card card;
    private Position startPosition;
    private Position targetPosition;
    private Card position;
    private MoveType type;

    public enum MoveType {
        MOVE, KING_MOVE, TURN, TOP_STACK_MOVE, DRAW
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setTargetPosition(int position, group_01.solverapi.model.Position.StackRow stackRow) {
        this.targetPosition.pos = position;
        this.targetPosition.stackRow = stackRow;
    }

    public void setStartPosition(int position, group_01.solverapi.model.Position.StackRow stackRow){
        this.startPosition.pos = position;
        this.startPosition.stackRow = stackRow;
    }

    public void setPosition(Card position){this.position = position;}

    public MoveType getType() {
        return type;
    }

    public Move(MoveType type) {
        this.type = type;
    }

    public String toString() {
        switch (type) {
            case MOVE:
                return String.format("Ryk %s til %s.", card.toString(), position.toString());
            case KING_MOVE:
                return String.format("Ryk %s til række %s.", card.toString(), targetPosition.toString());
            case TOP_STACK_MOVE:
                return String.format("Ryk %s til tops stakken", card.toString());
            case DRAW:
                return "Træk et kort fra bunken.";
            case TURN:
                return "Vend et kort.";
            default:
                return "Kan ikke finde et træk.";
        }
    }

    private class Position {
        private int pos;
        private group_01.solverapi.model.Position.StackRow stackRow;

        public Position(int pos, group_01.solverapi.model.Position.StackRow stackRow) {
            this.pos = pos;
            this.stackRow = stackRow;
        }
    }
}
