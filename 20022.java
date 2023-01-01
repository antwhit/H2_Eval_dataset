import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZipper {

    static final int BUFFER = 2048;

    public static boolean UnZip(String inFileName, String destinationDirectory) throws IOException {
        File sourceZipFile = new File(inFileName);
        File unzipDestinationDirectory = new File(destinationDirectory);
        ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);
        Enumeration zipFileEntries = zipFile.entries();
        while (zipFileEntries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(unzipDestinationDirectory, currentEntry);
            File destinationParent = destFile.getParentFile();
            destinationParent.mkdirs();
            if (!entry.isDirectory()) {
                BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
                int currentByte;
                byte data[] = new byte[BUFFER];
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }
        zipFile.close();
        return true;
    }
}
