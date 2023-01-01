public class Board {

    Piece[][] Grid = new Piece[3][3];

    int counter = 0;

    Board() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Grid[x][y] = new Piece('_', x, y);
                System.out.print(" " + Grid[x][y].val);
            }
            System.out.println();
        }
    }

    boolean isEmpty(int x, int y) {
        if (Grid[x][y].val == '_') {
            return true;
        } else {
            return false;
        }
    }

    void put(int x, int y, char v) {
        if (isEmpty(x, y) == true) {
            Grid[x][y].val = v;
            Grid[x][y].x = x;
            Grid[x][y].y = y;
            printBoard();
            counter++;
            winner();
        } else {
            printBoard();
            System.out.println();
            System.out.println(Grid[x][y].x + " & " + Grid[x][y].y + " is an invalid position");
        }
    }

    char winner() {
        for (int i = 0; i < 3; i++) {
            if (Grid[i][0].val == Grid[i][1].val && Grid[i][0].val == Grid[i][2].val && Grid[i][0].val != ' ') return Grid[i][0].val;
        }
        for (int i = 0; i < 3; i++) {
            if (Grid[0][i].val == Grid[1][i].val && Grid[0][i].val == Grid[2][i].val && Grid[0][i].val != ' ') return Grid[0][i].val;
        }
        if (Grid[0][0].val == Grid[1][1].val && Grid[0][0].val == Grid[2][2].val && Grid[0][0].val != ' ') return Grid[0][0].val;
        if (Grid[0][2].val == Grid[1][1].val && Grid[0][2].val == Grid[2][0].val && Grid[0][2].val != ' ') return Grid[0][2].val;
        return '_';
    }

    void printBoard() {
        System.out.println();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                System.out.print(" " + Grid[x][y].val);
            }
            System.out.println();
        }
    }
}
