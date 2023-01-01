import eprog.*;

public class CurrencyInput extends EprogIO {

    public int lowerValueLimit;

    public int upperValueLimit;

    public String inputString;

    public int value;

    public String currency;

    public CurrencyInput(String input) {
        inputString = input;
    }

    public int stringToInt(String string) {
        return new Integer(string).intValue();
    }

    public boolean checkStringFormat(String string) {
        if (string.length() < 2) return false;
        for (int i = 0; i < string.length() - 1; i++) {
            if (string.charAt(i) < '0' || string.charAt(i) > '9') return false;
        }
        char lastCharacter = string.charAt(string.length() - 1);
        if (lastCharacter < 'A' || lastCharacter > 'Z') return false;
        return true;
    }

    public boolean checkValueRange(int value) {
        if (value < lowerValueLimit || value > upperValueLimit) return false;
        return true;
    }

    public int getValue(String string) {
        String subString = string.substring(0, string.length() - 1);
        return stringToInt(subString);
    }

    public String getCurrency(String string) {
        return string.substring(string.length() - 1, string.length());
    }

    public boolean check() {
        if (!checkStringFormat(inputString)) return false;
        value = getValue(inputString);
        currency = getCurrency(inputString);
        if (!checkValueRange(value)) return false;
        return true;
    }
}
