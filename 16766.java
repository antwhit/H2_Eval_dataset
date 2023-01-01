import java.awt.Component;

/**
 * Linked list implementation of the list
 *    using a header node.
 * Access to the list is via timelineIterator.
 * @author Mark Allen Weiss
 * @see timelineIterator
 */
public class timeline {

    /**
     * Construct the list
     */
    public timeline() {
        header = new frame(null);
    }

    public Component add() {
        return null;
    }

    /**
     * Test if the list is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return header.next == null;
    }

    /**
     * Make the list logically empty.
     */
    public void makeEmpty() {
        header.next = null;
    }

    /**
     * Return an iterator representing the header node.
     */
    public timelineIterator zeroth() {
        return new timelineIterator(header);
    }

    /**
     * Return an iterator representing the first node in the list.
     * This operation is valid for empty lists.
     */
    public timelineIterator first() {
        return new timelineIterator(header.next);
    }

    /**
     * Insert after p.
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    public void insert(Object x, timelineIterator p) {
        if (p != null && p.current != null) p.current.next = new frame(x, p.current.next);
    }

    /**
     * Return iterator corresponding to the first node containing an item.
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public timelineIterator find(Object x) {
        frame itr = header.next;
        while (itr != null && !itr.element.equals(x)) itr = itr.next;
        return new timelineIterator(itr);
    }

    /**
     * Return iterator prior to the first node containing an item.
     * @param x the item to search for.
     * @return appropriate iterator if the item is found. Otherwise, the
     * iterator corresponding to the last element in the list is returned.
     */
    public timelineIterator findPrevious(Object x) {
        frame itr = header;
        while (itr.next != null && !itr.next.element.equals(x)) itr = itr.next;
        return new timelineIterator(itr);
    }

    /**
     * Remove the first occurrence of an item.
     * @param x the item to remove.
     */
    public void remove(Object x) {
        timelineIterator p = findPrevious(x);
        if (p.current.next != null) p.current.next = p.current.next.next;
    }

    public static void printList(timeline theList) {
        if (theList.isEmpty()) System.out.print("Empty list"); else {
            timelineIterator itr = theList.first();
            for (; itr.isValid(); itr.advance()) System.out.print(itr.retrieve() + " ");
        }
        System.out.println();
    }

    private frame header;

    public static int listSize(timeline theList) {
        timelineIterator itr;
        int size = 0;
        for (itr = theList.first(); itr.isValid(); itr.advance()) size++;
        return size;
    }

    public static void main(String[] args) {
        timeline theList = new timeline();
        timelineIterator theItr;
        int i;
        theItr = theList.zeroth();
        printList(theList);
        for (i = 0; i < 10; i++) {
            theList.insert(new Integer(i), theItr);
            printList(theList);
            theItr.advance();
        }
        System.out.println("Size was: " + listSize(theList));
        for (i = 0; i < 10; i += 2) theList.remove(new Integer(i));
        for (i = 0; i < 10; i++) if ((i % 2 == 0) == (theList.find(new Integer(i)).isValid())) System.out.println("Find fails!");
        System.out.println("Finished deletions");
        printList(theList);
    }
}
