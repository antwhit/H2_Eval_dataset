import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileApp {

    Map<Character, Integer> characterInt = new HashMap<Character, Integer>();

    public List<Character> getFreq() {
        List<Character> tempList = new ArrayList<Character>(characterInt.keySet());
        Collections.sort(tempList);
        return tempList;
    }

    public int lookupFreq(Character freq) {
        return characterInt.get(freq);
    }

    public void loadFile(File fileName) throws IOException {
        System.out.println("Calculating frequency for " + fileName.getAbsolutePath());
        characterInt.clear();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                char element = line.charAt(i);
                element = Character.toLowerCase(element);
                if (Character.isLetter(element)) {
                    Integer count = characterInt.get(element);
                    if (count == null) {
                        count = new Integer(0);
                    }
                    characterInt.put(element, ++count);
                }
            }
            line = reader.readLine();
        }
    }

    public List<File> getLocalFiles() {
        List<File> files = new ArrayList<File>();
        for (File contents : (new File("")).listFiles()) {
            if (contents.isFile() && contents.getName().endsWith(".txt")) {
                files.add(contents);
            }
        }
        return files;
    }
}
