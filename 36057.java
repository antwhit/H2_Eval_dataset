import data.ooimpl.CatalogItemImpl;
import data.IntegerValue;

public class Type2Item extends CatalogItemImpl {

    private int value;

    private String description;

    private boolean isLimitedEdition;

    public Type2Item(String name, String description, boolean isLimitedEdition, int value) {
        super(name, new IntegerValue(value));
        this.description = description;
        this.isLimitedEdition = isLimitedEdition;
    }

    protected CatalogItemImpl getShallowClone() {
        return new Type2Item(new String(getName()), description, isLimitedEdition, value);
    }
}
