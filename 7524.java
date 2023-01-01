import java.awt.Image;

/**
 * The addOn_imagedraw class draws the selected images upon the draw field.
 * 
 * @author (c) Copyright 2009 by authors: Sven Weber, Dr. The Anh Vuong
 *
 */
public class addOn_imageDraw {

    /** A value for the image. */
    static Image image;

    /** the coordinates of the image. */
    static int x = 0, y = 0;

    /** The scaling size of the image. */
    static int xscaling = 0;

    static int yscaling = 0;

    /** The default image. */
    String color = "Red";

    /** The name of the image. */
    static String imageName;

    /** A value for the DPanel class. */
    interface_DrawPanel DP = interface_DrawPanel.MainInstance;

    /**
	 * The default constructor.
	 */
    public addOn_imageDraw() {
    }

    /**
	 * Takes the image name and creates a new one.
	 * 
	 * @param name	The name of the image with his appendix, e.g. ".gif"
	 */
    public static void setImage(String name) {
        imageName = name;
        image = interface_MainApplet.MainInstance.getImage(addOn_toolBarFrame.class.getResource(name));
    }

    /**
	 * Displays the name of the image with his appendix.
	 * 
	 * @return	The name of the image.
	 */
    public static String getImageName() {
        return imageName;
    }

    /**
	 * Returns the current image object.
	 * 
	 * @return	The current image object.
	 */
    public static Image getImage() {
        return image;
    }

    /**
	 * Returns the correct X-Scaling for the images. It is the x-value minus
	 * the xscaling-value.
	 * 
	 * @return	The X-Scaling for the images.
	 */
    public static int getCorrectXScale() {
        return x - xscaling;
    }

    /**
	 * Returns the correct Y-Scaling for the images. It is the y-value minus
	 * the yscaling-value.
	 * 
	 * @return	The Y-Scaling for the images.
	 */
    public static int getCorrectYScale() {
        return y - yscaling;
    }

    /**
	 * Sets the x-value of the image.
	 * 
	 * @param xValue	The x-coordinate of the image.
	 */
    public static void setXCoordinate(int xValue) {
        x = xValue;
    }

    /**
	 * Sets the y-value of the image.
	 * 
	 * @param yValue	The y-coordinate of the image.
	 */
    public static void setYCoordinate(int yValue) {
        y = yValue;
    }

    /**
	 * Sets the selected color to the image. Please note, that the color is not
	 * really a java.awt.Color! Rather it is a synonym for a color! This synonym has to
	 * be implemented in another method.
	 * 
	 * @param color	The implemented color in String format.
	 */
    public void setColor(String color) {
        this.color = color;
    }

    /**
	 * Displays the color of the image.
	 * 
	 * @return	The color in String format.
	 */
    public String getColor() {
        return color;
    }

    /**
	 * Sets the x-value scaling for the images.
	 * 
	 * @param xscale	Scaling rate for the x-value.
	 */
    public static void setXScaling(int xscale) {
        xscaling = xscale;
    }

    /**
	 * Sets the y-value scaling for the images.
	 * 
	 * @param yscale	Scaling rate for the y-value.
	 */
    public static void setYScaling(int yscale) {
        yscaling = yscale;
    }

    /**
	 * Returns the x-value scaling.
	 * 
	 * @return The x-value scaling.
	 */
    public static int getXScaling() {
        return xscaling;
    }

    /**
	 * Returns the x-value scaling.
	 * 
	 * @return	The x-value scaling.
	 */
    public static int getYScaling() {
        return yscaling;
    }
}
