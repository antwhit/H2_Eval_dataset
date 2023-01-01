import java.util.Collection;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

/**<p>LinkedSet extends java.util.LinkedList to create a container class which has all of the *features of a LinkedList but in a set (no duplicates).  
  *It does this by overloading all of the add and addAll methods with versions which check for duplicates.</p>
  *<p>At this point if one makes an iterator for the list the iterator is an ordinary linkedListIterator thus allowing duplicates.
  *The class may be improved at a later date to fix this.</p>
  */
class LinkedSet extends java.util.LinkedList {

    /**Adds the object to the set iff it isn't already in the set
    */
    public boolean add(Object element) {
        boolean added = false;
        if (!contains(element)) {
            added = super.add(element);
        }
        return added;
    }

    /**Adds the object to the end of the set iff it isn't already in the set
    */
    public void addLast(Object element) {
        if (!contains(element)) {
            super.addLast(element);
        }
    }

    /**Adds the object to the begining of the set iff it isn't already in the set
    */
    public void addFirst(Object element) {
        if (!contains(element)) {
            super.addFirst(element);
        }
    }

    /**Adds the object to the list at the position named by the int iff it isn't already in the set.
    */
    public void add(int index, Object element) throws IndexOutOfBoundsException {
        if (index > this.size()) throw new IndexOutOfBoundsException();
        if (!this.contains(element)) {
            ListIterator thisIterator = this.listIterator(index);
            thisIterator.add(element);
        }
    }

    /**Adds each object from the Collection to the set which isn't in it already starting at the position marked by the int
    */
    public boolean addAll(int index, Collection collection) throws IndexOutOfBoundsException {
        if (index > this.size()) throw new IndexOutOfBoundsException();
        Object element;
        ListIterator thisIterator = this.listIterator(index);
        Iterator collectionIterator = collection.iterator();
        while (collectionIterator.hasNext()) {
            element = collectionIterator.next();
            if (!this.contains(element)) {
                thisIterator.add(element);
            }
        }
        return true;
    }

    /**Adds each object from the Collection to the set which isn't already in the set
    */
    public boolean addAll(Collection collection) {
        boolean success = true;
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.add(iterator.next())) success = false;
        }
        return success;
    }
}
