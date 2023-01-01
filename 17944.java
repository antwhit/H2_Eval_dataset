import java.util.ResourceBundle;
import java.io.File;

public class ToUpper {

    public static void main(String args[]) {
        ResourceBundle rb = ResourceBundle.getBundle("ImageUpdate");
        String imgStrPath = rb.getString("imgupdate.images");
        String uprStrPath = rb.getString("imgupdate.base") + args[0];
        File imgFilePath = new File(imgStrPath);
        String images[] = imgFilePath.list();
        content_block: for (int i = 0; i < images.length; i++) {
            if (!images[i].endsWith(".jpg") && !images[i].endsWith(".JPG")) continue content_block;
            if (!images[i].equals(images[i].toUpperCase())) {
                String imgStr = imgStrPath + File.separator + images[i];
                String uprStr = uprStrPath + File.separator + images[i].toUpperCase();
                File imgFile = new File(imgStr);
                File uprFile = new File(uprStr);
                System.out.println("Renaming Image to " + images[i].toUpperCase());
                imgFile.renameTo(uprFile);
            }
        }
    }
}
