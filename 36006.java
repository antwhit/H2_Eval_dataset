import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpStatus;

/**
 * This is a simple servlet that echoes back the request as a response to the client. 
 * Currently it supports HTTP GET, POST, PUT and DELETE operations.
 * 
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */
public class EchoServlet extends HttpServlet {

    /** Unique serial number for Serialization. */
    private static final long serialVersionUID = 1602540958258467298L;

    /**
     * Initializes the servlet.
     * 
     * @param config ServletConfig instance containing configuration information.
     * @throws ServletException if a servlet exception has occured.
     */
    public void init(ServletConfig config) throws ServletException {
        if (config == null) {
            System.err.println("Null config");
            return;
        }
        super.init(config);
    }

    /**
     * Get method for the EchoServlet. It accesses the request parameters and writes 
     * their names and values to the response.
     * 
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @throws ServletException if a servlet exception has occured.
     * @throws IOException if an I/O exception has occured.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration x = request.getParameterNames();
        while (x.hasMoreElements()) {
            String pname = (String) x.nextElement();
            response.getOutputStream().println(pname + "=" + request.getParameter(pname));
        }
    }

    /**
     * Post method for the EchoServlet. It simply dumps the request conetent as it is to the response.
     * 
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @throws ServletException if a servlet exception has occured.
     * @throws IOException if an I/O exception has occured.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getContentLength() <= 0) {
            response.getOutputStream().println("No data");
            return;
        }
        InputStream bis = request.getInputStream();
        ServletOutputStream bos = response.getOutputStream();
        byte[] buffer = new byte[request.getContentLength()];
        bis.read(buffer);
        bos.write(buffer);
    }

    /**
     * Put method for the EchoServlet. It simply puts the request conetent as it is to the response. 
     * 
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @throws ServletException if a servlet exception has occured.
     * @throws IOException if an I/O exception has occured.
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getContentLength() <= 0) {
            response.getOutputStream().println("No data");
            return;
        }
        InputStream bis = request.getInputStream();
        ServletOutputStream bos = response.getOutputStream();
        byte[] buffer = new byte[request.getContentLength()];
        bis.read(buffer);
        bos.write(buffer);
    }

    /**
     * Delete method for the EchoServlet. It sets the staus code of the response to HTTP/1.1 200 OK. 
     * 
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @throws ServletException if a servlet exception has occured.
     * @throws IOException if an I/O exception has occured.
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpStatus.SC_OK);
    }
}
