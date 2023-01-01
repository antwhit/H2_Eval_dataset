import java.util.ArrayList;
import modules.base.res.Category;
import modules.base.res.Partner;
import modules.base.res.User;
import org.mga.common.SearchKey;
import org.mga.core.ObjectPool;
import org.mga.tools.TypeConvertor;

public class Main extends Server {

    public void Main() {
    }

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        new Main();
        User u = new User();
        u.getName().setDval("Mantavya Gajjar");
        u.getLogin().setDval("admin");
        u.getPassword().setDval("admin");
        Category c = new Category();
        c.getCode().setDval("WSUP");
        c.getName().setDval("West Suppliers");
        int id = c.create();
        int pids[] = c.search(new SearchKey[] { new SearchKey("name", "ilike", "%", "AND") });
        if (pids != null) {
            c.getParentId().setDval(pids[0]);
        }
        Partner p = (Partner) ObjectPool.getInstance("modules.base.res.Partner");
        p.getName().setDval("Mantavya R Gajjar");
        p.getEmail().setDval("mantavyagajjar@gmail.com");
        p.getAge().setDval("30");
        p.getCategoryIds().setDval(TypeConvertor.toObjectArray(pids));
        int ids[] = p.search(new SearchKey[] { new SearchKey("name", "like", "%", "AND") });
        ArrayList<Partner> data = p.read(ids);
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}
