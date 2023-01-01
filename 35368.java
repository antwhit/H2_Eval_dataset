public class NestedLoopKO1 {

    static int nestedLoop(int n) {
        int s = 0;
        int i = 0;
        while (i <= n) {
            int j = 0;
            while (j <= n) {
                s = s + 1;
                j = j + 1;
            }
            i = i + 1;
        }
        return s;
    }
}
