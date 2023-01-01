import java.io.IOException;
import nick.syllable.DictionarySyllabilizer;
import nick.syllable.util.HaikuGenerator;

/**
 * This class is designed to provide some examples on how to use the the
 * DictionarySyllabilizer class. It looks up a given word and counts the number
 * of syllables. There are also some utility methods that allow users to access
 * the dictionary.
 * 
 * @author Nick Aschenbach (nick.aschenbach@gmail.com)
 * @version April 7th, 2009
 */
public class DSMain {

    public static void main(String args[]) {
        DictionarySyllabilizer ds = null;
        try {
            ds = new DictionarySyllabilizer("cmudict_0_6d.txt");
        } catch (IOException e) {
            System.out.println("Cannot read dictionary file!");
        }
        int syllables = 0;
        String[] str = ds.dictionary();
        for (int i = 0; i < str.length; i++) {
            syllables += ds.getSyllables(str[i]);
        }
        System.out.println("Words:          " + ds.dictionarySize());
        System.out.println("Syllables:      " + syllables);
        double ave = (double) syllables / (double) ds.dictionarySize();
        System.out.println("Syl / Word ave: " + ave);
        String str2 = ds.randomWord();
        System.out.println("Random word: " + str2 + " syllables: " + ds.getSyllables(str2));
        System.out.println("Randomly generated haiku:");
        HaikuGenerator hg = new HaikuGenerator();
        System.out.println(hg.haiku());
    }
}
