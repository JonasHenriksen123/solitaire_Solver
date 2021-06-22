package group_01.solverapi.model;

public class Move {

    private Card card;
    private Position startPosition;
    private Position targetPosition;
    private Position turnPosition;
    private MoveType type;

    public enum MoveType {
        MOVE, TURN, DRAW
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setTargetPosition(int position, Position.StackRow stackRow) {
        this.targetPosition = new LocalPos(position, stackRow);
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public Position getTurnPosition() {
        return turnPosition;
    }

    public Card getCard() {
        return card;
    }

    public void setStartPosition(int position, Position.StackRow stackRow){
        this.startPosition = new LocalPos(position, stackRow);
    }

    public void setTurnPosition(int position, Position.StackRow stackRow){
        this.turnPosition = new LocalPos(position, stackRow);
    }

    public MoveType getType() {
        return type;
    }

    public Move(MoveType type) {
        this.type = type;
    }

    public String toString() {
        switch (type) {
            case MOVE:
                return String.format("Move the %s from %s %s to %s %s.", card.toString(), startPosition.getStackRow(),
                        startPosition.getPosition() + 1, targetPosition.getStackRow(), targetPosition.getPosition()+ 1);
            case DRAW:
                return "Draw three cards from stock.";
            case TURN:
                return String.format("Turn card in %s %s.", turnPosition.getStackRow(), turnPosition.getPosition() + 1);
            default:
                return "Kan ikke finde et træk.";
        }
    }

    //region helper
    private class LocalPos extends Position {
        LocalPos(int position, StackRow stackRow) {
           this.setPosition(position);
           this.setStackRow(stackRow);
        }
    }
    //endregion
}
