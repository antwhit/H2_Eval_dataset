import java.util.ArrayList;

/** local boundary usage of DmaRelationship
*/
public class DmzLocalRel {

    DmzRelationship rel;

    private int toX;

    private int toY;

    String to;

    /** Creates new localRel
     * @param to  relationship direction
     * @param rel  DmzRelationship object
     * @param toX  boundary X co-ord
     * @param toY  boundary Y co-ord*/
    public DmzLocalRel(String to, DmzRelationship rel, int toX, int toY) {
        this.to = to;
        this.rel = rel;
        this.toX = toX;
        this.toY = toY;
    }

    /**  get the DmzRelationship
     * @return  DmzRelationship */
    public DmzRelationship getRel() {
        return this.rel;
    }

    /** return the relationship direction 
     * @return  "p" || "c" */
    public String getTo() {
        return this.to;
    }

    /** Getter for property toX.
     * @return Value of property toX.
     */
    public int getToX() {
        return toX;
    }

    /** Setter for property toX.
     * @param toX New value of property toX.
     */
    public void setToX(int toX) {
        this.toX = toX;
    }

    /** Getter for property toY.
     * @return Value of property toY.
     */
    public int getToY() {
        return toY;
    }

    /** Setter for property toY.
     * @param toY New value of property toY.
     */
    public void setToY(int toY) {
        this.toY = toY;
    }
}
