class P739 {

    public static void main(String[] args) {
        System.out.println("         NAME                     SOUNDEX CODE");
        try {
            String line = readLn();
            while (line != null) {
                System.out.print("         ");
                process(line);
                line = readLn();
            }
        } catch (Exception e) {
            while (true) {
            }
        }
        System.out.println("                   END OF OUTPUT");
    }

    private static void process(String line) {
        String answer = "";
        int code = getCode(line.charAt(0));
        answer += line.charAt(0);
        int length = line.length();
        for (int k = 1; k < length; k++) {
            char anotherLetter = line.charAt(k);
            int anotherCode = getCode(line.charAt(k));
            if (anotherCode != 0 && anotherCode != code) {
                answer += anotherCode;
            }
            code = anotherCode;
        }
        System.out.print(line);
        String space = "";
        for (int k = line.length() + 10; k < 35; k++) {
            space += " ";
        }
        if (answer.length() != 4) {
            if (answer.length() > 4) {
                answer = answer.substring(0, 4);
            } else {
                for (int k = answer.length(); k < 4; k++) {
                    answer += "0";
                }
            }
        }
        System.out.println(space + answer);
    }

    private static int getCode(char c) {
        switch(c) {
            case 'D':
                return 3;
            case 'T':
                return 3;
            case 'L':
                return 4;
            case 'M':
                return 5;
            case 'N':
                return 5;
            case 'R':
                return 6;
            case 'B':
                return 1;
            case 'F':
                return 1;
            case 'P':
                return 1;
            case 'V':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 2;
            case 'J':
                return 2;
            case 'K':
                return 2;
            case 'Q':
                return 2;
            case 'S':
                return 2;
            case 'X':
                return 2;
            case 'Z':
                return 2;
        }
        return 0;
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
