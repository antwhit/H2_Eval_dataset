import java.util.StringTokenizer;

class P11192_39643 {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        while (true) {
            String line = readLn();
            if ("0".equals(line)) break;
            StringTokenizer st = new StringTokenizer(line);
            int numberOfGroups = Integer.parseInt(st.nextToken());
            String word = st.nextToken();
            int i = word.length() / numberOfGroups;
            StringBuilder sb = new StringBuilder(word.length());
            for (int k = 1; k <= numberOfGroups; k++) {
                for (int j = (i * k); j > (k - 1) * i; j--) {
                    sb.append(word.charAt(j - 1));
                }
            }
            System.out.println(sb.toString());
        }
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
