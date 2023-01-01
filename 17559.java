public class Item {

    private char displayChar = '%';

    TileField parent;

    Item(TileField t) {
        parent = t;
    }

    char getDisplayChar() {
        return displayChar;
    }
}
