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

    public void setTargetPosition(int position, group_01.solverapi.model.Position.StackRow stackRow) {
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

    public void setStartPosition(int position, group_01.solverapi.model.Position.StackRow stackRow){
        this.startPosition = new LocalPos(position, stackRow);
    }

    public void setPosition(Position position){this.turnPosition = position;}

    public MoveType getType() {
        return type;
    }

    public Move(MoveType type) {
        this.type = type;
    }

    public String toString() {
        switch (type) {
            case MOVE:
                return String.format("Ryk %s fra %s %s til %s %s.", card.toString(), startPosition.getStackRow(),
                        startPosition.getPosition(), targetPosition.getStackRow(), targetPosition.getPosition());
            case DRAW:
                return "Træk tre kort fra bunken.";
            case TURN:
                return String.format("Vend kort i %s %s.", turnPosition.getStackRow(), turnPosition.getPosition());
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
