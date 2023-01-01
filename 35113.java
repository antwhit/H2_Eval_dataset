import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class UserList {

    public UserList() {
    }

    public boolean userListReached() {
        return true;
    }

    private static void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), "gbk");
        BufferedReader reader = new BufferedReader(read);
        String line;
        while ((line = reader.readLine()) != null) {
        }
    }
}
