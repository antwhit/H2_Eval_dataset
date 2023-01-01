class P865 {

    public static void main(String[] args) {
        int cases = Integer.parseInt(readLn());
        readLn();
        for (int k = 0; k < cases; k++) {
            process();
            if (k + 1 != cases) System.out.println();
        }
    }

    private static void process() {
        String plainAlphabet = readLn();
        String subAlphabet = readLn();
        int length = plainAlphabet.length();
        int[] table = new int[256];
        for (int k = 0; k < 256; k++) {
            table[k] = -1;
        }
        for (int k = 0; k < length; k++) {
            table[plainAlphabet.charAt(k)] = subAlphabet.charAt(k);
        }
        System.out.println(subAlphabet);
        System.out.println(plainAlphabet);
        String line = readLn();
        while (line != null && !line.equals("")) {
            length = line.length();
            String lineSub = "";
            for (int k = 0; k < length; k++) {
                char c = line.charAt(k);
                if (table[c] != -1) {
                    lineSub += (char) table[c];
                } else {
                    lineSub += c;
                }
            }
            System.out.println(lineSub);
            line = readLn();
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
        return (buffer.toString());
    }
}
