import java.awt.event.*;

/**
 * @author verrier
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PToolsRdr {

    public static void main(String[] args) {
        String versionInfo = "PToolsRdr(AC): Version 4.3.7 (12th December 2005)";
        System.out.println(versionInfo + "\n");
        String mappingFile = null;
        String taxonomyFile = null;
        String inputDir = null;
        String outputDir = null;
        boolean bSilentRun = false;
        String strFileSeparator = System.getProperty("file.separator");
        String strWinOn = getThisOption("-w", args);
        if (strWinOn == null) {
            System.out.println(printUsage(versionInfo));
            System.exit(0);
        }
        taxonomyFile = getThisOption("-t", args);
        if (taxonomyFile == null && strWinOn == "off") {
            System.out.println(printUsage(versionInfo));
            System.exit(0);
        }
        if (taxonomyFile.length() > 0) {
            System.out.println("Taxonomy file: " + taxonomyFile);
        }
        inputDir = getThisOption("-i", args);
        if (inputDir == null && strWinOn == "off") {
            System.out.println(printUsage(versionInfo));
            System.exit(0);
        }
        if (inputDir.length() > 0) {
            System.out.println("InputDirectory: " + inputDir);
            if (!inputDir.endsWith(strFileSeparator)) inputDir += strFileSeparator;
        }
        outputDir = getThisOption("-o", args);
        if (outputDir == null && strWinOn == "off") {
            System.out.println(printUsage(versionInfo));
            System.exit(0);
        }
        if (outputDir.length() > 0) {
            System.out.println("OutputDirectory: " + outputDir);
            if (!outputDir.endsWith(strFileSeparator)) outputDir += strFileSeparator;
            bSilentRun = true;
        }
        String strSetType = getThisOption("-s", args);
        if (strSetType == null && strWinOn == "off") {
            System.out.println(printUsage(versionInfo));
            System.exit(0);
        }
        if (getThisOption("-w", args) == "on") {
            bSilentRun = false;
        }
        SimpleFrame myFrame = new SimpleFrame();
        if (bSilentRun) {
            myFrame.setParas(strSetType, taxonomyFile, inputDir, outputDir);
            myFrame.Silent();
            System.exit(0);
        } else {
            myFrame.setViewFrame();
            myFrame.getViewFrame().addWindowListener(new WindowAdapter() {

                public void WindowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            myFrame.getViewFrame().setTitle("PToolsRdr");
            myFrame.getViewFrame().setResizable(true);
        }
    }

    private static String printUsage(String version) {
        return "Usage: java " + "PToolsRdr \n" + "[-Xss12M] [-Xmx1200M] \n" + "-w on | off \n" + "-s set type (==AC for AraCyc, ==MC for MetaCyc)\n" + "-t taxonomy file \n" + "-i input directory \n" + "-o output directory \n" + "[-? help]\n" + version + "\nPJ Verrier (RRes)";
    }

    private static String getThisOption(String strThisopt, String[] args) {
        String strRet = "";
        int nArgs = args.length;
        int n = 0;
        while (n < nArgs - 1) {
            if (args[n].equals(strThisopt)) {
                return (String) args[n + 1];
            }
            n++;
        }
        return null;
    }
}
