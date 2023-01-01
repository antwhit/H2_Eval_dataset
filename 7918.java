import java.io.*;

/**
 * Program to add the required ; characters to the DDL output from TogetherSoft so that we can use this DDL
 * file as input to the Ant SQL task.
 */
public class FixDDL {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage:  java FixDDL <input> <output>");
            System.exit(0);
        }
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
            String lineBuf = in.readLine();
            boolean skip = false;
            while (lineBuf != null) {
                lineBuf = lineBuf.trim();
                if (lineBuf.length() == 0) {
                    if (!skip) {
                        out.write(";");
                        skip = true;
                    }
                } else {
                    skip = false;
                    out.write(lineBuf);
                }
                out.newLine();
                lineBuf = in.readLine();
            }
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
