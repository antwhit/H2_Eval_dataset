import javax.microedition.io.*;
import java.io.*;

public class H {

    public HttpConnection c = null;

    public OutputStream o = null;

    public InputStream i = null;

    public int L(String url) {
        try {
            c = (HttpConnection) Connector.open(url);
            o = c.openOutputStream();
            return -1;
        } catch (Exception e) {
            c = null;
            return 0;
        }
    }

    public int L() {
        if (c != null) return -1;
        return 0;
    }

    public void c() {
        try {
            if (i != null) {
                i.close();
                i = null;
            }
            if (o != null) {
                o.close();
                o = null;
            }
            c.close();
            c = null;
        } catch (Exception e) {
            c = null;
        }
    }

    public void L(String header, String value) {
        try {
            c.setRequestProperty(header, value);
        } catch (Exception e) {
        }
    }

    public void c(String method) {
        try {
            c.setRequestMethod(method);
        } catch (Exception e) {
        }
    }

    public String i(String header) {
        try {
            String value;
            value = c.getHeaderField(header);
            if (value == null) value = "";
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    public int o(String message) {
        try {
            o.write(message.getBytes());
            return -1;
        } catch (Exception e) {
            o = null;
            return 0;
        }
    }

    public int o() {
        try {
            int code;
            code = c.getResponseCode();
            i = c.openInputStream();
            return code;
        } catch (Exception e) {
            return -1;
        }
    }

    public String j() {
        try {
            StringBuffer retval = new StringBuffer();
            int c;
            while ((c = i.read()) != -1) {
                retval.append((char) (char) c);
            }
            return retval.toString();
        } catch (Exception e) {
            return "";
        }
    }
}

;
