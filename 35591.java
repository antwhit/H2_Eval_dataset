import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import DE.FhG.IGD.semoa.web.HttpUtilities;

public class DateServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        FileInputStream in;
        String workDir;
        String page;
        byte[] data;
        workDir = getInitParameter("WORK_DIR");
        if (workDir == null) {
            workDir = ".";
        }
        in = new FileInputStream(workDir + "/data.html");
        data = new byte[in.available()];
        in.read(data);
        page = new String(data);
        data = null;
        page = HttpUtilities.cutComment(page, "<!--desc");
        data = HttpUtilities.replace(page, "!--time--", new Date().toString()).getBytes();
        page = null;
        res.setStatus(HttpServletResponse.SC_OK);
        res.setContentType("text/html");
        res.setContentLength(data.length);
        res.getOutputStream().write(data);
    }
}
