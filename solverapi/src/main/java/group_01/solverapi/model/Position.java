package group_01.solverapi.model;

public abstract class Position {
    private int position;
    private StackRow stackRow;

    public enum StackRow {
        TOP_STACKS("Top_Stacks"),
        BOTTOM_STACKS("Bottom_Stacks"),
        PLAY_STACK("Play_Stack");

        private String name;

        StackRow(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
           return name;
        }
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
        return String.format("Stak-index %s i %s", position, stackRow);
    }
}
