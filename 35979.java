import java.io.*;

public class ej9 {

    public static void main(String[] args) throws IOException {
        int num1, num2;
        InputStreamReader teclado;
        BufferedReader bufferLectura;
        String linea;
        teclado = new InputStreamReader(System.in);
        bufferLectura = new BufferedReader(teclado);
        System.out.print("Introduce el número 1: ");
        linea = bufferLectura.readLine();
        num1 = Integer.parseInt(linea);
        System.out.print("Introduce el número 2: ");
        linea = bufferLectura.readLine();
        num2 = Integer.parseInt(linea);
        if (num1 < num2) System.out.println("El Número 2 es mayor"); else if (num2 < num1) System.out.println("El Número 1 es mayor"); else System.out.println("Los números son iguales");
    }
}
