import com.ibm.JikesRVM.*;
import java.util.*;
import java.io.*;

class OsProcessException extends Exception {

    int status;

    public OsProcessException(int pstatus) {
        status = pstatus;
    }

    public OsProcessException(String msg) {
        super(msg);
    }
}
