import java.io.*;
import java.util.Date;

public class Test {

    private static BufferedWriter log;

    public static void main(String args[]) throws Exception {
        log = new BufferedWriter(new FileWriter("log.log", true));
        String argStr = "";
        for (int i = 0; i < args.length; i++) argStr += (args[i] + " ");
        writeToLog("Starting Test - args: " + argStr);
        try {
            while (true) {
                Thread.sleep(5000);
                writeToLog("Running Test...");
            }
        } catch (Exception e) {
            writeToLog("Exception: " + e);
        }
        writeToLog("Done!");
        try {
            log.close();
        } catch (Exception e) {
        }
    }

    public static synchronized void writeToLog(String msg) {
        try {
            if (log != null) {
                log.write(new Date() + ";" + msg + "\r\n");
                log.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
