import java.io.DataInputStream;
import java.io.IOException;

public class FormatBinBatimentLecteur {

    public FormatBinBatimentLecteur() {
    }

    public static int lireInfo(DataInputStream dis) throws IOException {
        short test = dis.readShort();
        int taille = (test - 13) / 2;
        byte test1 = dis.readByte();
        short test2 = dis.readShort();
        int test3 = dis.readInt();
        int test4 = dis.readInt();
        String bat = "";
        for (int i = 0; i <= taille; i++) {
            bat += dis.readChar();
        }
        System.out.println("" + test + test1 + test2 + test3 + test4 + bat);
        new Batiment(bat, test2, test3, test4);
        return test;
    }
}
