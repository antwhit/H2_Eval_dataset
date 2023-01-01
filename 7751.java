import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openrdf.query.Binding;
import org.openrdf.query.BindingSet;
import com.ontotext.ordi.iterator.CloseableIterator;
import com.ontotext.ordi.sar.core.CoreRepositoryClient;
import com.ontotext.ordi.sar.core.CoreRepositoryConnection;
import com.ontotext.ordi.sar.exception.SARException;

public class QueryServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;

    static CoreRepositoryClient client = new CoreRepositoryClient();

    CoreRepositoryConnection conn;

    public QueryServlet() {
        super();
        this.conn = client.getRepositoryConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        answer(request.getParameter("query"), response.getOutputStream());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        answer(request.getParameter("query"), response.getOutputStream());
    }

    private void answer(String sparql, ServletOutputStream out) throws IOException {
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.print(String.format("<!-- %s -->", System.getProperty("user.dir")));
        try {
            long start = System.currentTimeMillis();
            CloseableIterator<? extends BindingSet> result = this.conn.query(sparql);
            long time = System.currentTimeMillis() - start;
            int count = outputResult(result, out);
            out.print(String.format("<br/><hr/><span> %d bindings obtained in %f seconds (%d ms)</span>", count, time / 1000f, time));
        } catch (Throwable e) {
            outputError(e, out);
        }
        out.println("</body>");
        out.println("</html>");
    }

    private static int outputResult(CloseableIterator<? extends BindingSet> result, ServletOutputStream out) throws IOException {
        out.print("<table border='1'>");
        int counter = 0;
        while (result.hasNext()) {
            System.out.println(String.format("* %d", counter));
            BindingSet set = (BindingSet) result.next();
            out.print("<tr>");
            out.print(String.format("<td>%d</td><td>", counter));
            Iterator<Binding> iter = set.iterator();
            out.print("<table>");
            while (iter.hasNext()) {
                Binding entry = iter.next();
                String entryName = entry.getName();
                String entryValue = entry.getValue().toString();
                entryValue = entryValue.replace('~', '?');
                System.out.println(entryName + ":" + entryValue);
                out.print(String.format("<tr><td><b>%s</b></td><td>%s</td></tr>", entryName, entryValue));
            }
            out.print("</table>");
            out.print("</td></tr>");
            counter++;
        }
        out.print("</table>");
        return counter;
    }

    private static void outputError(Throwable e, ServletOutputStream out) throws IOException {
        out.print(String.format("ERROR: %s", e.toString()));
    }
}
