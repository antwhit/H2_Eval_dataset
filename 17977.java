/**
 * This interface is implemented by all collections in this example.
 * 
 * @author  Hridesh Rajan
 * @version $Revision: 1.2 $, $Date: 2010/03/01 00:30:55 $
 *
 */
public interface Collection {

    void add(Element element);

    void remove(Element element);

    boolean contains(Element element);
}
