import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

public class TextFile {

    private Vector<String> WordsIntoFile;

    private String DictionaryFileName;

    private Vector<RowandPosition> RowAndPositionOfWords;

    public TextFile(String DictionaryFileName) throws IllegalArgumentException {
        if (DictionaryFileName == null || DictionaryFileName.equals("")) {
            throw new IllegalArgumentException("Invalid File Name");
        }
        this.WordsIntoFile = new Vector<String>();
        this.DictionaryFileName = DictionaryFileName;
        RowAndPositionOfWords = new Vector<RowandPosition>();
    }

    /**
	 * Questo metodo estrae da un file ogni parola presente riconoscendola tramite un apposita esressione regolare
	 * 
	 * @return Un vector di stringhe, ogni suo elemento � una parola del file letto
	 */
    public Vector<String> ExtractWordsFromFile() {
        String inputFileName = this.DictionaryFileName;
        int Lines = 1;
        int count = 0;
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputFileName));
            String Line = in.readLine();
            String[] Temp = ExtractWordsFromLine(Line);
            while (count < Temp.length) RowAndPositionOfWords.add(new RowandPosition(Lines, count + 1));
            Lines++;
            AddAll(Temp);
            System.out.println(Line);
            while (Line != null) {
                Line = in.readLine();
                System.out.println(Line);
                Temp = ExtractWordsFromLine(Line);
                count = 0;
                while (count < Temp.length) this.RowAndPositionOfWords.add(new RowandPosition(Lines, count + 1));
                Lines++;
                AddAll(Temp);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println(inputFileName + " FileNotFound");
        } catch (IOException e) {
            System.out.println(" IOException  " + e);
        } catch (NullPointerException e) {
        }
        return WordsIntoFile;
    }

    /**
	 * Questo metodo estrae da una riga ogni parola presente riconoscendola tramite un apposita esressione regolare
	 * 
	 * @param Line La linea del file da cui estrarre le parole
	 * @return Un vettore di stringhe, ogni suo elemento � una parola estratta dalla riga
	 */
    public String[] ExtractWordsFromLine(String Line) {
        String[] WordsIntoLine = Line.split("[\\W]+");
        return WordsIntoLine;
    }

    /**
	 * Questo metodo aggiunge una o pi� parole all'interno del Vector che contiene tutte le stringhe
	 * 
	 * @param Words Array di stringhe
	 * @return False se il metodo non � riuscito ad aggiungere la/e stringa/e, true altrimenti
	 */
    private boolean AddAll(String[] Words) {
        int i = 0;
        for (i = 0; i < Words.length; i++) if (!this.WordsIntoFile.add(Words[i])) return false;
        return true;
    }

    /**
	 * Questo metodo aggiunge una o pi� parole all'interno del Vector che contiene tutte le stringhe
	 * 
	 * @param Words Array di stringhe
	 * @return False se il metodo non � riuscito ad aggiungere la/e stringa/e, true altrimenti
	 */
    private void EditWord(int Position, String WordChanged) {
        WordChanged = WordChanged.toLowerCase();
        this.RowAndPositionOfWords.get(Position).EditChanged(true);
        this.WordsIntoFile.set(Position, WordChanged);
    }

    /**
	 * Questo metodo restituisce il Vector di stringhe
	 * 
	 * @return Il Vector di stringhe
	 */
    public Vector<String> ReturnAllWords() {
        return this.WordsIntoFile;
    }
}
