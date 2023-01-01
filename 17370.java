import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class MimeTextHTML implements MimeDecoder {

    Vector TheMessage = null;

    public boolean CanDecode(Vector Header) {
        int i;
        boolean bCanDecode;
        String strCurrent;
        bCanDecode = false;
        for (i = 0; i < Header.size(); i++) {
            strCurrent = (String) Header.elementAt(i);
            strCurrent = strCurrent.toLowerCase();
            if (strCurrent.startsWith("content-type")) {
                if (strCurrent.indexOf("text/html") != -1) {
                    bCanDecode = true;
                }
            }
        }
        return bCanDecode;
    }

    public void Line(String strLine) {
        if (TheMessage == null) {
            TheMessage = new Vector();
        }
        TheMessage.addElement(strLine);
    }

    public void Done() {
    }

    public void DisplayBody(PrintWriter out) {
        int i;
        int size;
        if (TheMessage != null) {
            size = TheMessage.size();
            for (i = 0; i < size; i++) {
                out.println((String) TheMessage.elementAt(i));
            }
        }
    }

    public void Attachment(PrintWriter out) {
    }
}
