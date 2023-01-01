import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.*;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

public class Norme_carree_noir implements PlugInFilter {

    static final int R = 0, G = 1, B = 2;

    static final int limit = 128;

    public void run(ImageProcessor ip) {
        ColorProcessor cp = (ColorProcessor) ip;
        int[] RGB = new int[3];
        int min;
        int max;
        int c;
        for (int v = 0; v < cp.getHeight(); v++) {
            for (int u = 0; u < cp.getWidth(); u++) {
                cp.getPixel(u, v, RGB);
                min = Math.min(RGB[R], Math.min(RGB[G], RGB[B]));
                max = Math.max(RGB[R], Math.min(RGB[G], RGB[B]));
                if ((min < limit) && (max < limit)) {
                    c = 0;
                } else {
                    c = 255;
                }
                RGB[R] = c;
                RGB[G] = c;
                RGB[B] = c;
                cp.putPixel(u, v, RGB);
            }
        }
    }

    public int setup(String arg, ImagePlus imp) {
        return DOES_RGB;
    }
}
