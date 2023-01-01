/**
 *  Interface that specifies how to implement location panels on the farmyard.
 *
 *  <p>
 *  This figure illustrates a sample location-panel configuration
 *  (with a location components and three animal components):
 *  <p><img src="doc-files/location-panel.gif" width=368 height=216><p>
 *
 *  @author Henrik Eriksson
 *  @version 0.01
 *
 *  @see LocationComponent
 *  @see AnimalComponent
 */
public interface LocationPanel {

    /**
   *  Adds an animal component to the location.
   *
   *  @param ac the AnimalComponent to add
   */
    public void addAnimalComponent(AnimalComponent ac);

    /**
   *  Removes an animal component from the location.
   *
   *  @param ac the AnimalComponent to remove
   */
    public void removeAnimalComponent(AnimalComponent ac);

    /**
   *  Sets the size of the location.
   *
   *  @param large size flag. True if an enlarged location is wanted, otherwise false.
   */
    public void setLargeMode(boolean large);

    /**
   * Gets the location name. The name is taken from the location component.
   *
   * @return the location name as a String
   */
    public String getLocationName();

    /**
   *  Gets the factory that created this object.
   *
   *  @return the factory object
   */
    public LocationFactory getFactory();

    /**
   * Informs the location that an animal is about to enter.
   */
    public void setStatusAnimalEntering();

    /**
   * Sets the status text for the location.
   *
   * @param str the status text
   */
    public void setStatusText(String str);

    /**
   *  Performs the handshaking with the animal when it is added. Informs
   *  the animal about its location and other relevant information, such
   *  as animals already present at the location. Informs other animals
   *  at the location about the added animal.
   *
   *  @param ap the animal proxy for the new animal
   */
    public void addAnimalHandshake(AnimalProxy ap);

    /**
   *  Performs the handshaking with the animal when it is removed.
   *  Informs other animals that this animal has been removed.
   *
   *  @param ap the animal proxy for the animal
   */
    public void removeAnimalHandshake(AnimalProxy ap);
}
