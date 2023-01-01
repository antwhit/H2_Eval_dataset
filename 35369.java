import java.util.StringTokenizer;

public class StringTokenizerFactory {

    public StringTokenizer createTokenizer(String s, String token) {
        return new StringTokenizer(s, token);
    }
}

;
