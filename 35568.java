public class RowandPosition {

    public int Row;

    public int Position;

    public boolean Changed = false;

    public RowandPosition(int Row, int Position) {
        Row = Row;
        Position = Position;
    }

    public void EditChanged(boolean flag) {
        this.Changed = flag;
    }
}
