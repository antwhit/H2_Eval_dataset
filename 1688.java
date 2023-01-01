import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

class Example151 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.format("*** %-12s %-12s%n", "Encoding", "Canonical name");
        String s = "El Ni�o, s��, �r�sk�bing �, �clair, �2";
        writeIt(s, "US-ASCII");
        writeIt(s, "ISO-8859-1");
        writeIt(s, "UTF-8");
        writeIt(s, "UTF-16");
        writeIt(s, "UTF-16BE");
        writeIt(s, "UTF-16LE");
        writeIt(s, "Cp1252");
    }

    static void writeIt(String s, String enc) throws UnsupportedEncodingException {
        OutputStreamWriter osw = new OutputStreamWriter(System.out, enc);
        PrintWriter pw = new PrintWriter(osw);
        System.out.format("*** %-12s %-12s%n", enc, osw.getEncoding());
        pw.println(s);
        pw.println();
        pw.flush();
    }
}
