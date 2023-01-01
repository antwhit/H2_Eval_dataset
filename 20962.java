import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public interface MimeDecoder {

    public boolean CanDecode(Vector Header);

    public void Line(String strLine);

    public void Done();

    public void DisplayBody(PrintWriter out);

    public void Attachment(PrintWriter out);
}
