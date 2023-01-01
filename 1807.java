import java.io.*;

public class Circumference {

    public static void main(String[] args) throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String strRadius;
        int radius;
        double pi = Math.PI;
        double answer;
        System.out.print("What is the radius? ");
        strRadius = dataIn.readLine();
        radius = Integer.parseInt(strRadius);
        answer = pi * (double) radius * 2;
        System.out.println("The circumference of the circle is " + Math.round(answer));
        System.out.println();
    }
}
