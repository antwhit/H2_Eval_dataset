import java.util.StringTokenizer;
import java.util.Vector;

class P10452_39643 {

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
        int cases = Integer.parseInt(readLn());
        for (int k = 0; k < cases; k++) {
            StringTokenizer st = new StringTokenizer(readLn());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            char[][] land = new char[y][x];
            for (int i = 0; i < y; i++) {
                String line = readLn();
                for (int j = 0; j < x; j++) {
                    land[i][j] = line.charAt(j);
                }
            }
            process(land);
        }
    }

    private static void process(char[][] land) {
        Vector v = new Vector();
        int y = land.length - 1;
        int x = 0;
        for (int k = 0; k < land[y].length; k++) {
            if (land[y][k] == '@') {
                x = k;
                break;
            }
        }
        char c = land[y][x];
        String objective = "IEHOVA#";
        int index = 0;
        while (c != '#') {
            char o = objective.charAt(index);
            if (x > 0 && land[y][x - 1] == o) {
                x--;
                v.addElement("left");
            } else if (x + 1 < land[y].length && land[y][x + 1] == o) {
                x++;
                v.addElement("right");
            } else if (y > 0 && land[y - 1][x] == o) {
                y--;
                v.addElement("forth");
            }
            index++;
            c = land[y][x];
        }
        for (int k = 0; k < v.size(); k++) {
            System.out.print(v.elementAt(k));
            if (k + 1 != v.size()) System.out.print(" ");
        }
        System.out.println();
    }
}
