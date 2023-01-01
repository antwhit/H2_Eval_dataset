public class CIntersectTask implements Runnable {

    public int FromIndex;

    public int ToIndex;

    CIntersectTask(int aFromIndex, int aToIndex) {
        FromIndex = aFromIndex;
        ToIndex = aToIndex;
    }

    @Override
    public void run() {
        Main.FindIntersections(FromIndex, ToIndex);
    }
}
