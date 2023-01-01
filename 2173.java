import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import com.filepool.fplibrary.FPClip;
import com.filepool.fplibrary.FPLibraryConstants;
import com.filepool.fplibrary.FPLibraryException;
import com.filepool.fplibrary.FPPool;

/**
 * <p>
 * Title: RestoreContent
 * </p>
 * <p>
 * Description: Using the Centera Java API to restore a client-side saved C-Clip.
 * utilizing raw access.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: EMC Corp.
 * </p>
 */
public class RestoreContent {

    /**
	 * main
	 * 
	 */
    public static void main(String[] args) {
        int exitCode = 0;
        String poolAddress = "centera1.cascommunity.org";
        int CLIP_OPTIONS = 0;
        InputStreamReader inputReader = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(inputReader);
        try {
            System.out.print("Address of cluster[" + poolAddress + "]: ");
            String answer = stdin.readLine();
            if (!answer.equals("")) poolAddress = answer;
            System.out.println("Connecting to Centera cluster(" + poolAddress + ")");
            FPPool.setGlobalOption(FPLibraryConstants.FP_OPTION_OPENSTRATEGY, FPLibraryConstants.FP_LAZY_OPEN);
            FPPool thePool = new FPPool(poolAddress);
            System.out.print("File name to restore: ");
            String filename = stdin.readLine();
            if (filename.equals("")) throw new IllegalArgumentException("Invalid answer.");
            File cdfDataFile = new File(filename);
            StringTokenizer st = new StringTokenizer(cdfDataFile.getName(), ".");
            String clipID = st.nextToken();
            if (!cdfDataFile.canRead()) {
                throw new IllegalArgumentException("Could not open CDF data file \"" + filename + "\" for reading.");
            }
            FileInputStream cdfDataStream = new FileInputStream(cdfDataFile);
            FPClip theClip = new FPClip(thePool, clipID, cdfDataStream, CLIP_OPTIONS);
            System.out.print("Restoring clip ID: " + clipID + " to the Centera...");
            String theNewClipID = theClip.Write();
            System.out.println(" Ok.");
            if (!theNewClipID.equals(clipID)) {
                throw new IllegalArgumentException("The newly-stored ClipID \"" + theNewClipID + "\" does not match the stored clipID \"" + clipID + "\".");
            }
            theClip.Close();
            cdfDataStream.close();
            inputReader.close();
            stdin.close();
            System.out.println("C-Clip successfully restored.");
            thePool.Close();
            System.out.println("\nClosed connection to Centera cluster (" + poolAddress + ")");
        } catch (FPLibraryException e) {
            exitCode = e.getErrorCode();
            System.err.println("Centera Error: " + e.getMessage() + "(" + exitCode + ")");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            exitCode = -1;
        } catch (IOException e) {
            System.err.println("I/O Error occured: " + e.getMessage());
            e.printStackTrace();
            exitCode = -1;
        }
        System.exit(exitCode);
    }
}
