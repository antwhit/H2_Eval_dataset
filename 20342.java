public class Board {

    char[][] square = new char[8][8];

    char active_color;

    boolean whiteCanCastleKingside;

    boolean blackCanCastleKingside;

    boolean whiteCanCastleQueenside;

    boolean blackCanCastleQueenside;

    String en_passant;

    int halfmove;

    int fullmove;

    Board() {
        square[0][0] = 'R';
        square[1][0] = 'N';
        square[2][0] = 'B';
        square[3][0] = 'Q';
        square[4][0] = 'K';
        square[5][0] = 'B';
        square[6][0] = 'N';
        square[7][0] = 'R';
        square[0][7] = 'r';
        square[1][7] = 'n';
        square[2][7] = 'b';
        square[3][7] = 'q';
        square[4][7] = 'k';
        square[5][7] = 'b';
        square[6][7] = 'n';
        square[7][7] = 'r';
        for (int i = 0; i <= 7; i++) {
            square[i][1] = 'P';
            square[i][6] = 'p';
        }
        whiteCanCastleKingside = true;
        blackCanCastleKingside = true;
        whiteCanCastleQueenside = true;
        blackCanCastleQueenside = true;
        fullmove = 1;
        halfmove = 0;
        active_color = 'w';
    }

    char getSquare(int column, int row) {
        return square[column][row];
    }

    void setSquare(int column, int row, char c) {
        square[column][row] = c;
    }

    void setFullMove(int f) {
        fullmove = f;
    }

    int getFullMove() {
        return fullmove;
    }

    void setActiveColor(char ac) {
        active_color = ac;
    }

    void setEnPassant(String en) {
        en_passant = en;
    }

    String getEnPassant() {
        String dash = "-";
        if (en_passant == null) return dash; else return en_passant;
    }

    char getActiveColor() {
        return active_color;
    }

    void incrementHalfMove() {
        halfmove++;
    }

    void resetHalfMove() {
        halfmove = 0;
    }

    int getHalfMove() {
        return halfmove;
    }

    void CantKingsideCastle(String color) {
        if (color.equals("white")) whiteCanCastleKingside = false; else blackCanCastleKingside = false;
    }

    void CantQueensideCastle(String color) {
        if (color.equals("white")) whiteCanCastleQueenside = false; else blackCanCastleQueenside = false;
    }

    boolean isWhiteKingsideCastleAvailable() {
        return whiteCanCastleKingside;
    }

    boolean isBlackKingsideCastleAvailable() {
        return blackCanCastleKingside;
    }

    boolean isWhiteQueensideCastleAvailable() {
        return whiteCanCastleQueenside;
    }

    boolean isBlackQueensideCastleAvailable() {
        return blackCanCastleQueenside;
    }

    boolean anyCastlingAvailable() {
        if (whiteCanCastleKingside) return true; else if (whiteCanCastleQueenside) return true; else if (blackCanCastleQueenside) return true; else if (blackCanCastleKingside) return true; else return false;
    }
}
