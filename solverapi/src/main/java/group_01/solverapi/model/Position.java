package group_01.solverapi.model;

public abstract class Position {
    private int position;
    private StackRow stackRow;

    public enum StackRow {
        TOP_STACKS("Foundations"),
        BOTTOM_STACKS("Tableau"),
        PLAY_STACK("Talon");

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
        return String.format("Stak-index %s in %s", position, stackRow);
    }
}
