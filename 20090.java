import data.ooimpl.CatalogItemImpl;
import data.IntegerValue;

public class Type1Item extends CatalogItemImpl {

    private int value;

    public Type1Item(String name, int value) {
        super(name, new IntegerValue(value));
        this.value = value;
    }

    protected CatalogItemImpl getShallowClone() {
        return new Type1Item(new String(getName()), value);
    }
}
