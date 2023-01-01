public class P11554_39643 {

    public static void main(String[] args) {
        long[] S = new long[1000001];
        long R;
        S[3] = 0;
        S[4] = 1;
        long b;
        long n;
        for (int ni = 5; ni < 1000001; ni++) {
            n = ni;
            b = n / 2 + 1;
            R = n * (n - 1) - (n + 1) * (n - b) - b * (b - 1);
            S[ni] = S[ni - 1] + R;
        }
        int cases = Integer.parseInt(readLn());
        for (int k = 0; k < cases; k++) System.out.println(S[Integer.parseInt(readLn())]);
    }

    static String readLn() {
        String newLine = System.getProperty("line.separator");
        StringBuffer buffer = new StringBuffer();
        int car = -1;
        try {
            car = System.in.read();
            while ((car > 0) && (car != newLine.charAt(0))) {
                buffer.append((char) car);
                car = System.in.read();
            }
            if (car == newLine.charAt(0)) System.in.skip(newLine.length() - 1);
        } catch (java.io.IOException e) {
            return (null);
        }
        if ((car < 0) && (buffer.length() == 0)) return (null);
        return (buffer.toString()).trim();
    }
}
