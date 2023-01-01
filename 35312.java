package @modelPackage@;

import java.util.List;
import java.io.Serializable;

/**
 * BaseDAO - interface for data access objects
 * @param <T> entity data type (identifier for persistent class)
 * @param <ID> id property data type
 * @link https://www.hibernate.org/328.html
 */
public interface BaseDAO<T, ID extends Serializable> {

  T findById(ID id, boolean lock);
  List<T> findAll();
  List<T> findByExample(T exampleInstance);
  List<T> findByExample(T exampleInstance, String[] excludeProperties);
  T makePersistent(T entity);
  void makeTransient(T entity);
}
