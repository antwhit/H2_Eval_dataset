import eprog.*;

public class Texte extends EprogIO {

    private static final String Numbers = "1234567890";

    private static final String msgValErr = "FALSCHE EINGABE";

    private static final int maxStrings = 10;

    private static final int maxWidth = 55;

    private static final int minWidth = 3;

    private static final int minPartOfWidth = 3;

    private static Strings text = new Strings();

    private static int width;

    public static void main(String[] args) {
        if (getStrings() && (width >= minWidth) && (width <= maxWidth) && (width <= text.getString().length()) && (width >= (float) text.getString().length() / minPartOfWidth) && (text.checkSpecifications()) && (text.forceLines(width))) {
            println(text.getString());
            println(text.getWordCount() + " " + text.getBreakCount());
        } else {
            println(msgValErr);
        }
        ;
    }

    private static boolean getStrings() {
        String tString;
        try {
            for (int i = 0; i < maxStrings; i++) {
                tString = readWord();
                if (Numbers.indexOf(tString.charAt(0)) >= 0) {
                    width = Integer.valueOf(tString).intValue();
                    return true;
                } else {
                    text.append(tString);
                }
                ;
            }
            ;
            width = readInt();
        } catch (Exception e) {
            return false;
        }
        ;
        return true;
    }
}
