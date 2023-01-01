/**
 * @author Markus Plessing
 * class Chainlist : interlaced list with methods to add and remove nodes
 */
public class Chainlist {

    /** starting point of list */
    public Node head = new Node("head", null);

    /** endpoint of list */
    public Node end = new Node("end", null);

    /** Constructor for Chainlist */
    public Chainlist() {
        head.next = end;
        end.next = null;
    }

    /**
   * insertAfter : to insert a Object into the list after the defined Node
   * @param parent Node the parent Node to insert the object after.
   * @param name String the Name of the inserted Node
   * @param elements String[][] the elements of the Node
   */
    public void insertAfter(Node parent, String name, String[][] elements) {
        if ((parent != null) && (parent != end)) {
            Node newNode = new Node(name, elements);
            newNode.next = parent.next;
            parent.next = newNode;
        }
    }

    /**
   * deleteAfter : to delete a Node after the given one
   * @param parent Node the Node after which the deletion should be done 
   */
    public void deleteAfter(Node parent) {
        if ((parent != null) && (parent != end)) {
            Node delNode = parent.next;
            if (delNode != null) parent.next = delNode.next;
        }
    }

    /**
   * delete: delete the active node
   * @param active Node delete the active Node
   */
    public void delete(Node active) {
        if (active != null && active != end) {
            Node work = head;
            Node parent = null;
            while (work.next != active) {
                work = work.next;
            }
            parent = work;
            this.deleteAfter(parent);
        }
    }

    /**
   * getLastNode : get the last node of the list
   * @return Node the last Node
   */
    public Node getLastNode() {
        Node work = head;
        while (work.next != end) {
            work = work.next;
        }
        return work;
    }

    /** 
   * clearList : clean up the list ( mark as trash for the garbage collector )
   */
    public void clearList() {
        Node work = head, temp = null;
        while (work.next != end) {
            temp = work;
            work = work.next;
            temp = null;
        }
        head = temp;
        end = temp;
    }

    /**
   * getNodeAt : go to a defined Position within the list
   * @param position int The position in the list
   * @return the Node at position
   */
    public Node getNodeAt(int position) {
        Node work = head;
        int i = 0;
        if (position < 1) {
            return null;
        }
        while (i < position) {
            if (work.next != end) {
                work = work.next;
                i++;
            } else {
                return null;
            }
        }
        return work;
    }

    /**
   * printout: print the list out
   * */
    public void printout() {
        Node activeNode = head;
        while (activeNode != null) {
            System.err.println(activeNode.nodeName);
            activeNode = activeNode.next;
        }
    }
}

/**
 * class Node : the class for a chainlink
 * */
class Node {

    /** the next Node */
    public Node next;

    /** the name of the Node*/
    public String nodeName;

    /** the elements of the Node*/
    public String[][] elements;

    /**Construtor for the Node
  	 * @param name String the name of the Node
  	 * @param tableVals String[][] the elements of the Node
  	 */
    public Node(String name, String[][] tableVals) {
        nodeName = name;
        elements = tableVals;
    }
}
