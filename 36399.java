import java.io.*;

public class findnull {

    public static void main(String args[]) throws IOException {
        File dir = new File(args[0]);
        recurse(dir);
    }

    private static void recurse(File dir) throws IOException {
        File[] entries = dir.listFiles();
        for (int i = 0; i < entries.length; i++) {
            File f = entries[i];
            if (f.isDirectory()) recurse(f); else if (f.getName().endsWith(".html")) {
                check(f);
            }
        }
    }

    private static void check(File f) throws IOException {
        long len = f.length();
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] stuff = new byte[(int) len];
        bis.read(stuff, 0, stuff.length);
        for (int i = 0; i < stuff.length; i++) {
            if (stuff[i] == 0) {
                System.out.println(f.getName() + " contains nulls");
                break;
            }
        }
        bis.close();
    }
}
