import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class Main {

    public static void main(String[] args) {
        try {
            String inFile = "..\\temp\\CoreJavaUnformated.tmp";
            String outFile = "..\\temp\\CoreJavaPretty.tmp";
            new PrettyCode(inFile, outFile).process().close();
            if (PrettyCode.verify(inFile, outFile)) {
                PrintWriter out = new PrintWriter("..\\temp\\java_success");
                out.print("OK");
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
