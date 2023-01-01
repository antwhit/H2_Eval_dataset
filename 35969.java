import java.util.LinkedList;

public class StringParser {

    String toParse;

    String prefix;

    int numberOfCharactersFollowing;

    public StringParser(String toParse, String prefix, int numberOfCharactersFollowing) {
        this.toParse = toParse;
        this.prefix = prefix;
        this.numberOfCharactersFollowing = numberOfCharactersFollowing;
        --this.numberOfCharactersFollowing;
    }

    static final String[] str = new String[0];

    String[] cache;

    /**
		main function to call
	*/
    public String[] allOccurancesOf() {
        if (cache == null) {
            LinkedList list = new LinkedList();
            String next = null;
            while ((next = getNext()) != null) {
                list.add(next);
            }
            cache = (String[]) list.toArray(str);
        }
        return cache;
    }

    int currPos;

    private String getNext() {
        int idx = toParse.indexOf(prefix, currPos);
        if (idx == -1) return null;
        int start = idx + prefix.length();
        int end = start + numberOfCharactersFollowing;
        currPos = idx + 1;
        if ((end) > toParse.length()) return null;
        return toParse.substring(start, end);
    }

    public static void main(String[] args) {
        String toParse = "fuck12345fuck23456blablablabalfuck34567dsafdsfuck123";
        StringParser p = new StringParser(toParse, "fuck", 5);
        for (int i = 0; i != p.allOccurancesOf().length; ++i) {
            System.out.println("" + i + " : " + p.allOccurancesOf()[i]);
        }
    }
}
