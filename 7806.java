import java.util.Enumeration;

public abstract class OPT_SortedGraphNode extends OPT_SpaceEffGraphNode {

    public abstract Enumeration getInNodes();

    public abstract Enumeration getOutNodes();

    /**
   * put your documentation comment here
   * @param forward
   * @return 
   */
    public OPT_SortedGraphNode getSortedNext(boolean forward) {
        if (forward) return sortedNext; else return sortedPrev;
    }

    /**
   * put your documentation comment here
   * @return 
   */
    public OPT_SortedGraphNode getForwardSortedNext() {
        return sortedNext;
    }

    /**
   * put your documentation comment here
   * @return 
   */
    public OPT_SortedGraphNode getBackwardSortedNext() {
        return sortedPrev;
    }

    /**
   * put your documentation comment here
   * @param next
   * @param forward
   */
    public void setSortedNext(OPT_SortedGraphNode next, boolean forward) {
        if (forward) sortedNext = next; else sortedPrev = next;
    }

    public void setForwardSortNumber(int number) {
        forwardSortNumber = number;
    }

    /**
   * put your documentation comment here
   * @return 
   */
    public int getForwardSortNumber() {
        return forwardSortNumber;
    }

    /**
   * put your documentation comment here
   * @param number
   */
    public void setBackwardSortNumber(int number) {
        backwardSortNumber = number;
    }

    /**
   * put your documentation comment here
   * @return 
   */
    public int getBackwardSortNumber() {
        return backwardSortNumber;
    }

    public void setSortNumber(int number, boolean forward) {
        if (forward) forwardSortNumber = number; else backwardSortNumber = number;
    }

    /**
   * put your documentation comment here
   * @param forward
   * @return 
   */
    public int getSortNumber(boolean forward) {
        if (forward) return forwardSortNumber; else return backwardSortNumber;
    }

    /**
   * put your documentation comment here
   * @param number
   */
    public void setSortNumber(int number) {
        forwardSortNumber = number;
    }

    public static int getNewSortMarker(OPT_SortedGraphNode anchor) {
        if (currentSortMarker == Integer.MAX_VALUE) {
            OPT_SortedGraphNode current;
            for (current = anchor; current != null; current = current.sortedPrev) {
                current.sortMarker = Integer.MIN_VALUE;
            }
            for (current = anchor; current != null; current = current.sortedNext) {
                current.sortMarker = Integer.MIN_VALUE;
            }
            currentSortMarker = Integer.MIN_VALUE;
        }
        ;
        return ++currentSortMarker;
    }

    int sortMarker = Integer.MIN_VALUE;

    private static int currentSortMarker = Integer.MIN_VALUE;

    /**
   * put your documentation comment here
   * @return 
   */
    int getSortMarker() {
        return sortMarker;
    }

    /**
   * put your documentation comment here
   * @param sortMarker
   */
    void setSortMarker(int sortMarker) {
        this.sortMarker = sortMarker;
    }

    /**
   * put your documentation comment here
   * @param sortMarker
   * @return 
   */
    boolean isSortMarkedWith(int sortMarker) {
        return (this.sortMarker >= sortMarker);
    }

    protected OPT_SortedGraphNode sortedPrev = null, sortedNext = null;

    protected int forwardSortNumber;

    protected int backwardSortNumber;
}
