import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Listing1803 {

    public static void main(String[] args) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("/tmp/two_in_one.txt"));
            writer.write("This is just a test.");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Can not open file.");
        }
    }
}
