import java.io.File;

public class Main {

    public static void main(String[] args) {
        MessageFrame mf = null;
        try {
            mf = new MessageFrame();
            mf.setVisible(true);
            String updateurl = "http://coopnet.sourceforge.net/latestClient.php";
            if (updateurl == null) {
                throw new Exception("Can't find update URL!");
            }
            MessageFrame.startedDownload();
            FileDownloader.downloadFile(updateurl, new File("./dist.zip").getCanonicalPath());
            MessageFrame.setMessage("Extracting files");
            UnZipper.UnZip("./dist.zip", new File("./UPDATER_TMP/").getCanonicalPath());
            new File("./dist.zip").delete();
            MessageFrame.setMessage("Updating client");
            FileMover.copyDirectory(new File("./UPDATER_TMP/Coopnet"), new File("."));
            FileMover.delete(new File("./UPDATER_TMP"));
            mf.setVisible(false);
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("java -jar CoopnetClient.jar");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            MessageFrame.setMessage("<html>An error occured while updating:<br>" + e.getLocalizedMessage());
        }
    }
}
