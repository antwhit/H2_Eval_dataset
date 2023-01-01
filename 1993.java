import java.util.*;

public class ProjectEuler {

    private static List<Character> getCharacterList(char[] charArray) {
        List<Character> newCharArray = new ArrayList<Character>();
        for (Character character : charArray) newCharArray.add(character);
        return newCharArray;
    }

    private static void getPandigitalStrings(String string, List<String> pandigitals, List<Character> characters) {
        if (characters.size() <= 0) pandigitals.add(string); else {
            for (Character character : characters) {
                List<Character> remainingCharacters = (List<Character>) ((ArrayList<Character>) characters).clone();
                remainingCharacters.remove(character);
                getPandigitalStrings(string + String.valueOf(character), pandigitals, remainingCharacters);
            }
        }
    }

    public static Long[] getPandigitals(final Long number) {
        List<String> pandigitalStrings = new ArrayList<String>();
        List<Character> charList = getCharacterList(number.toString().toCharArray());
        getPandigitalStrings("", pandigitalStrings, charList);
        Set<Long> numbers = new TreeSet<Long>();
        for (String numberString : pandigitalStrings) numbers.add(Long.valueOf(numberString));
        return numbers.toArray(new Long[numbers.size()]);
    }

    public static boolean isPandigital(final Long number) {
        boolean isPandigital = true;
        String numberString = String.valueOf(number);
        for (int a = 1; a <= numberString.length(); a++) {
            if (numberString.indexOf(String.valueOf(a)) == -1) {
                isPandigital = false;
            }
        }
        return isPandigital;
    }

    public static boolean isPrime(final double number) {
        if (number % 2 == 0) {
            return false;
        }
        double squareRoot = Math.sqrt(number);
        if (squareRoot == Math.floor(squareRoot)) {
            return false;
        }
        for (int i = 3; i < squareRoot; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] getDivisors(Integer number) {
        List<Integer> divisors = new ArrayList<Integer>();
        for (int a = 1; a <= number / 2; a++) if (number % a == 0) divisors.add(a);
        return divisors.toArray(new Integer[divisors.size()]);
    }

    public static Long getFactorial(Long number) {
        Long factorial = 1L;
        for (Long counter = 1L; counter <= number; counter++) factorial *= counter;
        return factorial;
    }
}
