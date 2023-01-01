import java.io.*;

public class IO {

    public static void main(String[] Jim) throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        String decNum;
        String binNum;
        boolean done = false;
        while (!done) {
            try {
                System.out.println();
                System.out.println("Enter the decimal number for the ascii letter");
                System.out.println();
                System.out.println("\t65)  A");
                System.out.println("\t66)  B");
                System.out.println("\t67)  C");
                decNum = dataIn.readLine();
                num = Integer.parseInt(decNum);
                if (num < 65 || num > 67) {
                    System.out.println("\tThe number must be between 65 and 67.");
                    throw new NumberFormatException();
                }
                done = true;
            } catch (NumberFormatException e) {
                System.out.println("\tYour response was not a valid number.");
                System.out.println("\tPlease reenter using the allowable numeric values.");
                System.out.println();
            }
        }
        done = false;
        binNum = getBinary(num);
        System.out.println();
        System.out.println("\tThe binary equivalent is " + binNum);
    }

    public static String getBinary(int findBin) {
        String theBinary;
        switch(findBin) {
            case 65:
                theBinary = "01000001";
                break;
            case 66:
                theBinary = "01000010";
                break;
            case 67:
                theBinary = "01000011";
                break;
            default:
                throw new NumberFormatException();
        }
        return theBinary;
    }
}
