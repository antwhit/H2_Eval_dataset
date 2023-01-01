public class T1414l13 {

    public static void main(String[] args) {
        a: do {
            try {
                try {
                    break a;
                } finally {
                    throw new Exception();
                }
            } catch (Throwable t) {
            }
            int i = 1;
        } while (false);
    }
}
