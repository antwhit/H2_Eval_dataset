import java.util.Vector;

class P200_39643 {

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

    public static void main(String[] args) {
        String line = readLn();
        Vector lines = new Vector(10000);
        while (line != null && !"#".equals(line)) {
            lines.addElement(line);
            line = readLn();
        }
        process(lines);
    }

    private static void process(Vector lines) {
        Vector queue = new Vector();
        for (int k = 0; k < 22; k++) {
            for (int i = 0; i < lines.size(); i++) {
                String line = (String) lines.elementAt(i);
                if (k < line.length()) {
                    Character c = new Character(line.charAt(k));
                    if (!queue.contains(c)) {
                        queue.addElement(c);
                    }
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < queue.size(); k++) sb.append(queue.elementAt(k));
        System.out.println(sb);
    }
}
