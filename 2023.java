import javax.sql.rowset.*;
import com.sun.rowset.*;
import java.util.*;
import java.lang.*;
import java.sql.*;
import javax.sql.RowSet;
import java.io.Serializable;

public class Range3 implements Predicate {

    private Object lo[];

    private Object hi[];

    private int idx[];

    public Range3(Object[] lo, Object[] hi, int[] idx) {
        this.lo = lo;
        this.hi = hi;
        this.idx = idx;
    }

    public boolean evaluate(RowSet rs) {
        boolean bool1 = false, bool2 = false;
        try {
            CachedRowSet crs = (CachedRowSet) rs;
            for (int i = 0; i < idx.length; i++) {
                if (((rs.getObject(idx[i]).toString()).compareTo(lo[i].toString()) < 0) || ((rs.getObject(idx[i]).toString()).compareTo(hi[i].toString()) > 0)) {
                    bool2 = true;
                } else {
                    bool1 = true;
                }
            }
        } catch (SQLException e) {
        }
        if (bool2) {
            return false;
        } else {
            return true;
        }
    }

    public boolean evaluate(Object value, String columnName) {
        return false;
    }

    public boolean evaluate(Object value, int columnIndex) {
        return false;
    }
}
