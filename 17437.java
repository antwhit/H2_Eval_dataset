import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
* Base class for parsers
*/
public abstract class AbstractParser {

    /**
        Directory for images 
    */
    public static final String IMAGE_BASE = "images" + System.getProperty("file.separator");

    /**
        Parses given file line by line. 
        @param filename name of file to parse
    */
    public void parse(String filename) {
        try {
            File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    return;
                }
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("#")) {
                    parseLine(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
        Parses line. 
        @param line one line of file
    */
    public abstract void parseLine(String line);

    /**
        Helper method. Loads image and waits till its loaded.
        @param fileName name of image file
        @return Image object
    */
    public static Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }
}
