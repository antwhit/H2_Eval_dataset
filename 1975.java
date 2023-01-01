import org.jdbf.engine.caching.CacheException;
import org.jdbf.engine.criteria.*;
import org.jdbf.engine.mapping.MappingException;
import org.jdbf.engine.sql.*;
import org.jdbf.engine.transaction.TransactionException;

/**
 * <code>DeleteSample.java</code>
 * 
 * @author Giovanni Martone<br>
 * @version $Revision: 1.4 $<br>
 * last changed by $Author: gmartone $
 */
public class DeleteSample extends Sample {

    /**
     * @throws Exception
     */
    public DeleteSample() throws Exception {
        super();
    }

    public void deleteObject() throws QueryException, MappingException, CacheException {
        Product prod = new Product("product");
        prod.setOID(new Integer(14));
        prod.setName("AS70 Canon");
        prod.setPrice(300);
        prod.setGroupId(0);
        int rows = database.delete(prod);
        printRowsAffected(rows);
    }

    public void deleteWithCriteria(String repositoryViewName) throws QueryException, MappingException {
        DeleteCriteria deleteCriteria = new DeleteCriteria(repositoryViewName);
        deleteCriteria.addSelectLike("name", "AS70%");
        int rows = database.deleteForCriteria(deleteCriteria);
        printRowsAffected(rows);
    }

    public static void main(String[] args) {
        DeleteSample sample = null;
        try {
            sample = new DeleteSample();
            sample.beginTransaction();
            sample.deleteObject();
            sample.commitTransaction();
        } catch (Exception e) {
            try {
                sample.rollbackTransaction();
            } catch (TransactionException txe) {
                txe.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            sample.close();
        }
    }
}
