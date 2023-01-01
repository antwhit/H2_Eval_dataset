import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class BuildTime {

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("USAGE: java BuildTime file");
            System.exit(0);
        }
        String filename = args[0];
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("Z yyyy/MM/dd HH:mm:ss");
        String value = formatter.format(date);
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(value);
            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("BuildTime:" + value);
    }
}
