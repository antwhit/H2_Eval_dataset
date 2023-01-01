import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A loader osztály felelős a paraméterül kapott elrési úton található file betöltéséért, illeteve ez
 * az osztály kezeli a filehoz tartozó flag-et, ami a kapcsolók állapotát tartalmazza.
 * 
 * @author Gulyás Máté
 */
public class Loader {

    private BufferedReader file;

    private File[] fileArray;

    private String fileName;

    private int fileFlag;

    /**
	 * A kontruktor olvassa be a file-t, illetve inicalizálja a fileFlaget, hogy
	 * később lekérdezhető legyen a filehoz tarozó flagek állapota.
	 *
	 * @param fileName
	 */
    public Loader(String fileName) {
        try {
            this.file = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            file = null;
        }
        try {
            fileFlag = new Integer(file.readLine()).intValue();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        fileArray = new File[2];
    }

    /**
	 *	Visszadja a loaaderFilehoz tartozó flaget.
	 * @return	int, amely a filehoz tartozó flaget jelöli. Ebből bitmaszkolással
	 *  lehet kiszedni a megfelelő kapcsolat állapotát.
	 */
    public int getFileFlag() {
        return fileFlag;
    }

    /**
	 * Hívásra a függvény beolvassa loaderFile-ból a következő sort, és ebből betölti
	 * a követekező tesztfilokat.
	 * @return A tesztfileok referenciáit tartalmazó tömb.
	 */
    public File[] getFile() {
        try {
            fileName = file.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(2);
        }
        if (fileName == null) return null;
        try {
            fileArray[0] = new File(fileName + ".exp");
            fileArray[1] = new File(fileName + ".out");
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            fileArray[0] = null;
            fileArray[1] = null;
        }
        return fileArray;
    }
}
