import java.util.List;

/**
 *
 */
public class GeneralIdentifier {

    /**
	 * private field 
	 * <li> this value must be unique among all classes
	 * <li> it is initialized to null
	 */
    private String id = null;

    /**
	 * private field 
	 * <li> you may have more GeneralIdentifier with the same name but with different Id
	 * <li> it is initialized to null
	 */
    private String name = null;

    /**
	 * private field 
	 * <li> it keeps the list of MakupItem using a specific Generalidentifier
	 * <li> it is initialized to null
	 */
    private List<MarkupItem> listOfMarkupItem = null;

    /**
	 * Constructor
	 * @return new empty instance of GeneralIdentifier
	 */
    public GeneralIdentifier() {
    }

    /**
	 * Constructor
	 * @return new instance of GeneralIdentifier
	 */
    public GeneralIdentifier(String id, String nameGI) {
        setId(id);
        setName(nameGI);
    }

    /**
	 * <li> it gives a list of all elements that use a specific GeneralIdentifier
	 * @return List of MarkupItem elements
	 */
    public List<MarkupItem> isUsedBy() {
        return listOfMarkupItem;
    }

    /**
	 * public Method
	 * allows you to obtain the name of GeneralIdentifier
	 * @return the name
	 */
    public String name() {
        return this.name;
    }

    /**
	 * public Method
	 * <li> allows you to assign the name to GeneralIdentifier
	 * @param name the name to GeneralIdentifier
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * @param id the id to set
	 */
    public void setId(String id) {
        this.id = id;
    }

    /**
	 * @return the id
	 */
    public String getId() {
        return id;
    }
}
