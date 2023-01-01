import java.io.*;

public class Logger {

    private PrintWriter out;

    public Logger(String filename) {
        try {
            this.out = new PrintWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String str) {
        try {
            this.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.out.close();
    }
}
