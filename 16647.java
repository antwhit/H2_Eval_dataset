import java.security.*;
import java.math.*;
import java.net.*;
import java.io.*;

public class DiffieHellmanInitiator {

    static BufferedReader k = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        SecureRandom sr = new SecureRandom();
        PrimeGenerator pg = new PrimeGenerator(1025, 10, sr);
        BigInteger[] pandg = pg.getSafePrimeAndGenerator();
        BigInteger x = new BigInteger(pandg[0].bitLength() - 1, sr);
        BigInteger gtox = pandg[1].modPow(x, pandg[0]);
        System.out.println("Enter host name or IP address of server:");
        String host = k.readLine();
        Socket socket = new Socket(host, 11111);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());
        out.println(pandg[0]);
        out.println(pandg[1]);
        out.println(gtox);
        BigInteger gtoy = new BigInteger(in.readLine());
        BigInteger key = gtoy.modPow(x, pandg[0]);
        System.out.println("The secret key is:\n" + key);
        k.readLine();
    }
}
