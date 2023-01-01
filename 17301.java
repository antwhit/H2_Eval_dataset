import java.util.*;
import java.io.*;

public class checkECL {

    private boolean DO = false;

    private boolean printedAbort = false;

    checkECL() {
    }

    public void setCheck() {
        DO = true;
    }

    public boolean getCheck() {
        return DO;
    }

    public void setPrintedAbort() {
        printedAbort = true;
    }

    public boolean getPrintedAbort() {
        return printedAbort;
    }
}
