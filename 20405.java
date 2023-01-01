public interface TTT_Engine {

    public String getEngineName();

    public String getEngineVersion();

    public boolean resetEngine(int dimension);

    public boolean setDimension(int dimension);

    public boolean setTargetLine(int targetLine);

    public boolean setBoardPosition(int[][] board, int colorToMove, int targetLine);

    public int makeMove(int colorToMove);

    public int makeMove(int[][] board, int colorToMove, int timeInMilliseconds, int depth);

    public int makeMove(int colorToMove, int timeInMilliseconds);

    public int makeMove(int colorToTime, int timeInMilliseconds, int depth);
}
