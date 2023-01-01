import java.io.*;

public class Multiply {

    public static void main(String[] args) throws IOException {
        int multiplier;
        int correct;
        boolean done = false;
        System.out.println("\t\tWelcome to the Multiplication Quiz");
        System.out.println("");
        multiplier = getNumber();
        correct = takeQuiz(multiplier);
        System.out.println("\t\tYou got " + correct + " correct!");
    }

    public static int getNumber() throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String inputData;
        int multiplier;
        System.out.println("Enter the multiplication table you wish to practice:");
        inputData = dataIn.readLine();
        multiplier = Integer.parseInt(inputData);
        return multiplier;
    }

    public static int takeQuiz(int multiplier) throws IOException {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String inputData;
        int answer;
        int count = 0;
        int correct = 0;
        while (count <= 12) {
            System.out.println("What is " + count + " times " + multiplier + "?");
            inputData = dataIn.readLine();
            answer = Integer.parseInt(inputData);
            if (answer == count * multiplier) {
                System.out.println("\tCorrect!");
                correct = correct + 1;
            } else {
                System.out.println("\tIncorrect");
            }
            count = count + 1;
        }
        return correct;
    }
}
