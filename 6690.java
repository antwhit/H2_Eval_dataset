import java.net.URL;

/**
 *  Class that makes it possible to define components for
 *  locations. This standard implementation manages basic
 *  location components. It simulates a barn.
 *  <p><img src="http://www.ida.liu.se/~TDDB64/pics/barn.gif">
 *
 *  @author Henrik Eriksson
 *  @version 0.01
 *  @see LocationPanel
 *  @see StandardLocationPanel
 *  @see AnimalComponent
 */
public class StandardLocationComponent extends ScaledImageCanvas implements LocationComponent {

    /**
   *  The name of the location.
   *  @serial
   */
    private String location_name;

    /**
   *  The scale factor for display-large mode.
   *  @serial
   */
    private double largeScale;

    /**
   *  The scale factor for display-small mode.
   *  @serial
   */
    private double smallScale;

    /**
   *  Creates a standard location component (barn).
   *
   *  @param image a string representing the picture (as a resource file)
   *  @param largeScale the scale when the component is in large mode
   *  @param smallScale the scale when the component is in small mode
   *  @param name the name of the location
   */
    public StandardLocationComponent(String image, double largeScale, double smallScale, String name) {
        super(image, smallScale);
        this.largeScale = largeScale;
        this.smallScale = smallScale;
        location_name = name;
    }

    /**
   *  Creates a standard location component (barn).
   *
   *  @param image a string representing the picture (as a URL)
   *  @param largeScale the scale when the component is in large mode
   *  @param smallScale the scale when the component is in small mode
   *  @param name the name of the location
   */
    public StandardLocationComponent(URL image, double largeScale, double smallScale, String name) {
        super(image, smallScale);
        this.largeScale = largeScale;
        this.smallScale = smallScale;
        location_name = name;
    }

    /**
   * Returns the location name.
   *
   * @return the location name as a String
   */
    public String getLocationName() {
        return location_name;
    }

    /**
   *  Sets the size of the location.
   *
   *  @param large size flag. True if an enlarged location is wanted, otherwise false.
   */
    public void setLargeMode(boolean large) {
        setScale((large) ? largeScale : smallScale);
    }
}
