import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import java.math.BigDecimal;
import java.util.*;

public class _Group extends EOGenericRecord {

    public _Group() {
        super();
    }

    public String name() {
        return (String) storedValueForKey("name");
    }

    public void setName(String value) {
        takeStoredValueForKey(value, "name");
    }

    public Group parentGroup() {
        return (Group) storedValueForKey("parentGroup");
    }

    public void setParentGroup(Group value) {
        takeStoredValueForKey(value, "parentGroup");
    }

    public NSArray persons() {
        return (NSArray) storedValueForKey("persons");
    }

    public void setPersons(NSMutableArray value) {
        takeStoredValueForKey(value, "persons");
    }

    public void addToPersons(Person object) {
        NSMutableArray array = (NSMutableArray) persons();
        willChange();
        array.addObject(object);
    }

    public void removeFromPersons(Person object) {
        NSMutableArray array = (NSMutableArray) persons();
        willChange();
        array.removeObject(object);
    }

    public NSArray subGroups() {
        return (NSArray) storedValueForKey("subGroups");
    }

    public void setSubGroups(NSMutableArray value) {
        takeStoredValueForKey(value, "subGroups");
    }

    public void addToSubGroups(Group object) {
        NSMutableArray array = (NSMutableArray) subGroups();
        willChange();
        array.addObject(object);
    }

    public void removeFromSubGroups(Group object) {
        NSMutableArray array = (NSMutableArray) subGroups();
        willChange();
        array.removeObject(object);
    }
}
