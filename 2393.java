import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.text.StringCharacterIterator;
import java.util.Iterator;

public class CustomTokenizer {

    private List<String> tokens;

    public CustomTokenizer(String s) {
        StringCharacterIterator iter = new StringCharacterIterator(s);
        tokens = new ArrayList<String>();
        for (char c = iter.first(); c != StringCharacterIterator.DONE; c = iter.next()) {
            String st = "";
            if (c == '(') {
                st = "(";
                Stack<String> stack = new Stack<String>();
                stack.push("(");
                while (!stack.empty()) {
                    c = iter.next();
                    if (c == '(') stack.push("("); else if (c == ')') stack.pop();
                    st += c;
                }
            } else if (c != ',') {
                while ((c != ',') && (c != StringCharacterIterator.DONE)) {
                    st += c;
                    c = iter.next();
                }
            }
            tokens.add(st);
        }
    }

    public List<String> getTokens() {
        return tokens;
    }

    public static void main(String[] args) {
        String s = "a,(b,c(d,e,(f,g))),h";
        CustomTokenizer ct = new CustomTokenizer(s);
        List<String> tokens = ct.getTokens();
        Iterator iter = tokens.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println(tokens.size());
    }
}
