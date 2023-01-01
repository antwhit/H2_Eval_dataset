import sifter.*;
import sifter.rcfile.*;
import sifter.translation.*;
import sifter.ui.*;
import sifter.messages.*;
import net.sourceforge.jmisc.Debug;
import java.io.*;

public class Sample3 {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Sample3 is getting ready to run.");
        FileOutputStream file = new FileOutputStream("samp3.out");
        ObjectOutputStream out = new ObjectOutputStream(file);
        String one = "this is one";
        String two = "this is two";
        String three = "this is three";
        out.writeUTF("This is the start");
        out.writeUTF(one);
        out.writeUTF("Here is one:");
        out.writeObject(one);
        out.writeUTF("Here is one again:");
        out.writeObject(one);
        out.writeUTF("Here is two:");
        out.writeObject(two);
        String a = one + " cat";
        String c = " cat";
        String d = one + c;
        out.writeUTF("Here is a:");
        out.writeObject(a);
        out.writeUTF("Here is c:");
        out.writeObject(c);
        out.writeUTF("Here is d:");
        out.writeObject(d);
        out.close();
    }
}
