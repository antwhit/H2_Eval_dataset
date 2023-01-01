import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WGet implements Runnable {

    private String url;

    private String outputDirectory;

    public WGet(String url, String outputDirectory) {
        this.url = url;
        this.outputDirectory = outputDirectory;
    }

    private void getFile() {
        try {
            URL u = new URL(url);
            u.openConnection();
            InputStream reader = u.openStream();
            String format = '.' + url.split("\\.")[url.split("\\.").length - 1];
            String fileName = Integer.toHexString(Long.toBinaryString(System.currentTimeMillis()).hashCode());
            FileOutputStream writer = new FileOutputStream(this.outputDirectory + fileName + format);
            byte[] buffer = new byte[153600];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
                buffer = new byte[153600];
            }
            writer.close();
            reader.close();
            System.out.println("Downloading " + getMinUrl() + " complete" + "\n");
        } catch (MalformedURLException ex1) {
            System.err.println("Incorrect URL - " + getMinUrl());
        } catch (FileNotFoundException ex2) {
            System.err.println("File not found on current url - " + getMinUrl() + "\n");
        } catch (IOException ex3) {
            System.err.println("Error downloading - " + getMinUrl() + "\n");
            ex3.printStackTrace(System.err);
        }
    }

    private String getMinUrl() {
        if (this.url.length() > 50) {
            return this.url.subSequence(0, 20) + "..." + this.url.subSequence(this.url.length() - 17, this.url.length());
        }
        return url;
    }

    public void run() {
        getFile();
    }
}
