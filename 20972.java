import java.util.Locale;
import unbbayes.util.Debug;

/**
 * This is just a stub in order to test this plugin
 * on UnBBayes
 * @author Shou Matsumoto
 * @author Danilo
 */
public class StartMonteCarlo {

    /**
	 * It just delegates to UnBBayes' main
	 * @param args
	 */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en"));
        Debug.setDebug(true);
        unbbayes.Main.main(args);
    }
}
