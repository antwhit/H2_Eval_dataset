import java.io.*;
import java.util.*;

public class parser {

    public Hashtable parse(String file, int[] tablekey) {
        String inputline = new String();
        String[] processed_inputline;
        Hashtable input = new Hashtable();
        int[] key = tablekey;
        try {
            String filename = new String(file);
            BufferedReader in = new BufferedReader(new FileReader(filename));
            inputline = in.readLine();
            processed_inputline = inputline.split("\\^");
            int l = key.length;
            String hashkey = new String();
            for (int i = 0; i < l; i++) {
                hashkey = hashkey + processed_inputline[key[i - 1]];
            }
            while (inputline != null) {
                input.put(processed_inputline, hashkey);
                inputline = in.readLine();
                processed_inputline = inputline.split("\\^");
                hashkey = "";
                for (int i = 0; i < l; i++) {
                    hashkey = hashkey + processed_inputline[key[i - 1]];
                }
            }
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
        }
        return input;
    }
}
