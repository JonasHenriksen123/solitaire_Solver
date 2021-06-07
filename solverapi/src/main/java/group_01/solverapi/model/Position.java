package group_01.solverapi.model;

public abstract class Position {
    private int position;
    private StackRow stackRow;

    public enum StackRow {
        TOP_STACKS,
        BOTTOM_STACKS,
        PLAY_STACK,
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return this.position;
    }
    public void setStackRow(StackRow stackRow) {
        this.stackRow = stackRow;
    }
    public StackRow getStackRow() {
        return this.stackRow;
    }

    @Override
    public String toString() {
        return String.format("Stack-index %s in %s", position, stackRow);
    }
}
