import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class JakartaFtpWrapper extends FTPClient {

    public boolean connectAndLogin(String host, String userName, String password) throws IOException, UnknownHostException, FTPConnectionClosedException {
        boolean success = false;
        connect(host);
        int reply = getReplyCode();
        if (FTPReply.isPositiveCompletion(reply)) success = login(userName, password);
        if (!success) disconnect();
        return success;
    }

    public void setPassiveMode(boolean setPassive) {
        if (setPassive) enterLocalPassiveMode(); else enterLocalActiveMode();
    }

    public boolean ascii() throws IOException {
        return setFileType(0);
    }

    public boolean binary() throws IOException {
        return setFileType(2);
    }

    public boolean downloadFile(String serverFile, String localFile) throws IOException, FTPConnectionClosedException {
        FileOutputStream out = new FileOutputStream(localFile);
        boolean result = retrieveFile(serverFile, out);
        out.close();
        return result;
    }

    public boolean uploadFile(String localFile, String serverFile) throws IOException, FTPConnectionClosedException {
        FileInputStream in = new FileInputStream(localFile);
        boolean result = storeFile(serverFile, in);
        in.close();
        return result;
    }

    public Vector listFileNames() throws IOException, FTPConnectionClosedException {
        FTPFile[] files = listFiles();
        Vector v = new Vector();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) v.addElement(new StringBuilder(String.valueOf(i + 1)).append(". ").append(files[i].getName()).append(" ").append(files[i].getSize()).append(" B").toString());
        }
        return v;
    }

    public String listFileNamesString() throws IOException, FTPConnectionClosedException {
        return vectorToString(listFileNames(), "\n");
    }

    public Vector listSubdirNames() throws IOException, FTPConnectionClosedException {
        FTPFile[] files = listFiles();
        Vector v = new Vector();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) v.addElement(files[i].getName());
        }
        return v;
    }

    public String listSubdirNamesString() throws IOException, FTPConnectionClosedException {
        return vectorToString(listSubdirNames(), "\n");
    }

    private String vectorToString(Vector v, String delim) {
        StringBuffer sb = new StringBuffer();
        String s = "";
        for (int i = 0; i < v.size(); i++) {
            sb.append(s).append((String) v.elementAt(i));
            s = delim;
        }
        return sb.toString();
    }
}
