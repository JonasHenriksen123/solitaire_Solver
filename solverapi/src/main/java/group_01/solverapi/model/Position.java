package group_01.solverapi.model;

public abstract class Position {
    int position;
    StackRow stackRow;

    public enum StackRow {
        TOP_STACKS,
        BOTTOM_STACKS
    }

    public abstract void setPosition(int position);
    public abstract int getPosition();
    public abstract void setStackRow(StackRow stackRow);
    public abstract StackRow getStackRow();

    @Override
    public String toString() {
        return String.format("Stack-index %s in %s", position, stackRow);
    }
}
