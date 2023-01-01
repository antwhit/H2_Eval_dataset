class P468 {

    public static void main(String[] args) {
        int cases = Integer.parseInt(readLn());
        for (int k = 0; k < cases; k++) {
            readLn();
            String notEncoded = readLn();
            String encoded = readLn();
            process(notEncoded, encoded);
            if (k + 1 != cases) System.out.println();
        }
    }

    private static void process(String notEncoded, String encoded) {
        int[] notEncodedFreq = new int[256];
        for (int k = 0; k < notEncoded.length(); k++) {
            notEncodedFreq[notEncoded.charAt(k)]++;
        }
        int[] encodedFreq = new int[256];
        for (int k = 0; k < encoded.length(); k++) {
            encodedFreq[encoded.charAt(k)]++;
        }
        char[] notEncodedAlphabet = new char[256];
        char[] encodedAlphabet = new char[256];
        for (int k = 0; k < 256; k++) {
            notEncodedAlphabet[k] = (char) k;
            encodedAlphabet[k] = (char) k;
        }
        for (int k = 0; k < 256; k++) {
            for (int j = k + 1; j < 256; j++) {
                if (encodedFreq[k] > encodedFreq[j]) {
                    swap(encodedFreq, k, j);
                    swap(encodedAlphabet, k, j);
                }
                if (notEncodedFreq[k] > notEncodedFreq[j]) {
                    swap(notEncodedFreq, k, j);
                    swap(notEncodedAlphabet, k, j);
                }
            }
        }
        char[] table = new char[256];
        for (int k = 0; k < 256; k++) {
            table[encodedAlphabet[k]] = notEncodedAlphabet[k];
        }
        String answer = "";
        for (int k = 0; k < encoded.length(); k++) {
            answer += table[encoded.charAt(k)];
        }
        System.out.println(answer);
    }

    private static void swap(char[] array, int k, int j) {
        char aux = array[k];
        array[k] = array[j];
        array[j] = aux;
    }

    private static void swap(int[] array, int k, int j) {
        int aux = array[k];
        array[k] = array[j];
        array[j] = aux;
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
