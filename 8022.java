import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.videostore.model.User;
import com.videostore.model.Users;
import com.videostore.model.WareHouse;

public class UserAdd extends GenericServlet {

    @Override
    protected void doGetProtected(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        User user = getCurrentUser(request);
        WareHouse w = new WarehouseFactory().load();
        Users us = new WarehouseFactory().loadUsers();
        Page page = new Page();
        page.setUser(user);
        page.setWarehouse(w);
        page.setXslt("E:/User/add.xslt");
        page.sectionAdd("user", null, null);
        page.print(out);
    }

    @Override
    protected void doPostProtected(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("newUsername");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String ppParam = request.getParameter("puntipromo");
        String citta = request.getParameter("citta");
        String cap = request.getParameter("cap");
        String via = request.getParameter("via");
        Users us = WarehouseFactory.loadUsers();
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setName(name);
        u.setSurname(surname);
        try {
            u.setPuntiPromo(Integer.parseInt(ppParam));
        } catch (Exception e) {
            Page ep = ErrorPage.createPropFormat("punti promo");
            ep.print(out);
            return;
        }
        u.setCitta(citta);
        u.setVia(via);
        u.setCap(cap);
        us.getUsers().put(u.getId(), u);
        WarehouseFactory.saveUsers(us);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/User/Search"));
    }
}
