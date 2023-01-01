import java.io.*;

public class Input {

    public static void main(String[] Jim) throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String letter;
        boolean done = false;
        while (!done) {
            System.out.println();
            System.out.println("\t Enter the letter A or B");
            System.out.println();
            letter = dataIn.readLine();
            if (letter.equals("A") || letter.equals("B")) {
                System.out.println("\t You did real good.");
                done = true;
            } else System.out.println("\t Your response was not valid.");
        }
    }
}
