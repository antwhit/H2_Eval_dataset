import java.awt.image.BufferedImage;

/**
 * Represents an image to process.
 * Wraps a BufferedImage to store the actual image data.
 * @author Sean Mooney
 *
 */
public class Image {

    private BufferedImage image;

    /**
    * Construct a new image object from a java.awt.image.BufferedImage.
    * @param img
    */
    public Image(BufferedImage img) {
        this.image = img;
    }

    /**
    * Return the pixel array representing the image
    * @return
    */
    public int[] getPixels() {
        int w = image.getWidth();
        int h = image.getHeight();
        int[] rgbs = new int[w * h];
        image.getRGB(0, 0, w, h, rgbs, 0, w);
        return rgbs;
    }
}
