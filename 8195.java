import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int numPortRecepteurProcess1 = 8082;
        int numPortRecepteurProcess2 = 8083;
        int numPortRecepteurProcess3 = 8084;
        int numPortRecepteurProcess4 = 8085;
        int numPortRecepteurProcess5 = 8086;
        Process process1 = new Process(1, numPortRecepteurProcess1, numPortRecepteurProcess2);
        Process process2 = new Process(2, numPortRecepteurProcess2, numPortRecepteurProcess3);
        Process process3 = new Process(3, numPortRecepteurProcess3, numPortRecepteurProcess4);
        Process process4 = new Process(4, numPortRecepteurProcess4, numPortRecepteurProcess5);
        Process process5 = new Process(5, numPortRecepteurProcess5, numPortRecepteurProcess1);
        process1.lanceClient();
        process2.lanceClient();
        process3.lanceClient();
        process4.lanceClient();
        process5.lanceClient();
        process1.lanceReception();
        process2.lanceReception();
        process3.lanceReception();
        process4.lanceReception();
        process5.lanceReception();
        process1.lanceEmission();
        process2.lanceEmission();
        process3.lanceEmission();
        process4.lanceEmission();
        process5.lanceEmission();
        process1.lancePere();
        process2.lancePere();
        process3.lancePere();
        process4.lancePere();
        process5.lancePere();
    }
}
