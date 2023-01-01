import java.text.DecimalFormat;

public class MainReplace {

    public static void main(final String[] args) {
        String s = "dilfgz8g'ntghzthdiflgnsd9l8fgnsd98fg7ns'd98fg7ns''d98fg7nsd98fgnsd98fg'7nsd98fgn7sd9'8fg";
        String mod;
        long tStart;
        long tStop;
        tStart = System.nanoTime();
        mod = replace(s);
        tStop = System.nanoTime();
        System.out.println("Elapsed Time: " + new DecimalFormat("00,000,000,000").format(tStop - tStart));
        System.out.println(mod);
        tStart = System.nanoTime();
        mod = escape(s);
        tStop = System.nanoTime();
        System.out.println("Elapsed Time: " + new DecimalFormat("00,000,000,000").format(tStop - tStart));
        System.out.println(mod);
    }

    private static String replace(final String s) {
        return s.replaceAll("'", "''");
    }

    public static String escape(final String s) {
        if (s == null) return null;
        final int len = s.length();
        final StringBuilder sb = new StringBuilder((int) (len * 1.1 + 4));
        final char[] cs = new char[len];
        s.getChars(0, len, cs, 0);
        char c = 0;
        sb.append('\'');
        for (int i = 0; i < len; i++) {
            c = cs[i];
            sb.append(c);
            if (c == '\'') sb.append(c);
        }
        sb.append('\'');
        return sb.toString();
    }
}
