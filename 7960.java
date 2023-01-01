import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

class XYFunction {

    public int startgeneration = -1, endgeneration = -1;

    public int[] _size_gen;

    private FloatArray2D _f;

    public static final int _xsize = 0;

    public static final int _ysize = 1;

    private int maxX, maxY;

    private double _fmax;

    int lastgen = -1;

    int lastindex = -1;

    /**
	 * 
	 * 
	 * 	ONLY CPU
	 * 
	 */
    public int getMaxX() {
        return _size_gen[_xsize];
    }

    public int getMaxY() {
        return _size_gen[_ysize];
    }

    public double fmax() {
        return _fmax;
    }

    public XYFunction(Image image, String color, double maxValue, double setborder) throws Exception {
        _size_gen = new int[2];
        double fmax = -1.0;
        maxX = image.getWidth(null);
        maxY = image.getHeight(null);
        _size_gen[_xsize] = maxX;
        _size_gen[_ysize] = maxY;
        _f = new FloatArray2D(maxX, maxY);
        if (maxX == 0 || maxY == 0) throw new Exception("Image map height or width is 0, this is unacceptable.");
        BufferedImage buffImage = new BufferedImage(maxX, maxY, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        for (int r = 0; r < maxY; r++) {
            for (int c = 0; c < maxX; c++) {
                int pixel = buffImage.getRGB(c, r);
                if (color.equalsIgnoreCase("red")) pixel = (pixel >> 16) & 0xff; else if (color.equalsIgnoreCase("green")) pixel = (pixel >> 8) & 0xff; else if (color.equalsIgnoreCase("blue")) pixel = pixel & 0xff; else throw new Exception("Color attribute \"" + color + "\" unrecognized.");
                _f.set(r, c, (float) (((double) pixel / 255.0) * maxValue));
                if (((r == 0 || r == maxY - 1) || (c == 0 || c == maxX - 1)) && setborder != -1.0) _f.set(r, c, (float) setborder);
                if (_f.get(r, c) > fmax) fmax = _f.get(r, c);
            }
        }
        _fmax = fmax;
    }

    public XYFunction(String filename, double setborder) throws Exception {
        _size_gen = new int[2];
        double fmax = -1.0;
        BufferedReader buff = new BufferedReader(new FileReader(filename));
        ArrayList<StringTokenizer> rows = new ArrayList<StringTokenizer>();
        while (buff.ready()) {
            rows.add(new StringTokenizer(buff.readLine(), " "));
        }
        for (int r = 0; r < rows.size(); r++) {
            if (rows.get(0).countTokens() != rows.get(r).countTokens()) throw new Exception("Uneven number of columns across rows in xy input file -- row " + (r + 1));
        }
        _size_gen[_xsize] = rows.get(0).countTokens();
        _size_gen[_ysize] = rows.size();
        maxX = rows.get(0).countTokens();
        maxY = rows.size();
        _f = new FloatArray2D(maxX, maxY);
        for (int r = 0; r < maxY; r++) {
            for (int c = 0; c < maxX; c++) {
                _f.set(r, c, Float.parseFloat(rows.get(r).nextToken()));
                if (((r == 0 || r == maxY - 1) || (c == 0 || c == maxX - 1)) && setborder != -1.0) _f.set(r, c, (float) setborder);
                if (_f.get(r, c) > fmax) fmax = _f.get(r, c);
            }
        }
        _fmax = fmax;
    }

    /**
	 * 
	 * 
	 * GPU AND CPU
	 */
    public float f(int x, int y) {
        return _f.get(y, x);
    }

    public FloatArray2D getF() {
        return _f;
    }

    public int toX(double lon, double minlon, double maxlon, int _size) {
        if (lon >= maxlon) return _size_gen[_size] - 1;
        if (lon <= minlon) return 0;
        return (int) Math.floor(_size_gen[_size] * (lon - minlon) / (maxlon - minlon));
    }

    public int toX(double lon, double minlon, double maxlon) {
        if (lon >= maxlon) return maxX - 1;
        if (lon <= minlon) return 0;
        return (int) Math.floor(maxX * (lon - minlon) / (maxlon - minlon));
    }

    public int toY(double lat, double minlat, double maxlat) {
        if (lat >= maxlat) return maxY - 1;
        if (lat <= minlat) return 0;
        return (int) Math.floor(maxY * (lat - minlat) / (maxlat - minlat));
    }
}
