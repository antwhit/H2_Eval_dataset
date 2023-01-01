/**
 * Inspection of this file should result in zero observations.
 *
 * @author Sweder Schellens
 * @version %full_filespec: %
 */
public class NoObservations {

    /**
   * Default constructor.
   */
    public NoObservations() {
        int x = 0;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
        x += 1;
    }

    /**
   * Check for equality.
   *
   * @param obj the object to compare this object to
   * @return <tt>true</tt> if this object equals obj
   */
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            result = false;
        } else {
            final NoObservations other = (NoObservations) obj;
            result = this.equalsInternally(other);
        }
        return (result);
    }

    /**
   * Returns a hash code value for this object.
   *
   * @return the hash code
   */
    public int hashCode() {
        final int value = 0;
        return (value);
    }

    /**
   * Returns a clone of this object.
   *
   * @return a clone
   * @throws CloneNotSupportedException when <tt>super.clone()</tt> throws it
   * @see java.lang.Object#clone
   */
    public Object clone() throws CloneNotSupportedException {
        final NoObservations clone = (NoObservations) super.clone();
        return (cloneInternally(clone));
    }

    /**
   * Invokes <tt>super.toString()</tt>.
   *
   * @return the string
   */
    public String toString() {
        return (super.toString());
    }

    private boolean equalsInternally(NoObservations obj) {
        final boolean result = obj != null;
        return (result);
    }

    private Object cloneInternally(NoObservations clone) {
        return (clone);
    }
}
