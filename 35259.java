import java.io.*;
import java.util.*;

public class DojoFileStorageProvider {

    public String load(String filePath) throws IOException, FileNotFoundException {
        StringBuffer results = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = null;
        while ((line = reader.readLine()) != null) {
            results.append(line);
        }
        reader.close();
        return results.toString();
    }

    public void save(String filePath, String content) throws IOException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
        writer.print(content);
        writer.close();
    }

    public void remove(String filePath) throws IOException, FileNotFoundException {
        File f = new File(filePath);
        if (f.exists() == false || f.isDirectory()) {
            return;
        }
        if (f.exists() && f.isFile()) {
            f.delete();
        }
    }
}
