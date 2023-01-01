import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import com.webobjects.eocontrol.*;

public class Group extends _Group {

    public NSArray sortedPersons() {
        EOSortOrdering userTypeOrdering = EOSortOrdering.sortOrderingWithKey("type", EOSortOrdering.CompareAscending);
        NSArray sortOrderings = new NSArray(userTypeOrdering);
        return EOSortOrdering.sortedArrayUsingKeyOrderArray(persons(), sortOrderings);
    }
}
