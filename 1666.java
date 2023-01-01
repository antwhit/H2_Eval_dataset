import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This is a filter that fills the restaurant Edit Details form with the data from the current account.
 * 
 * @author iwharris
 *
 */
public class EditRestaurantFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = response.getWriter();
        res.setHeader("Cache-Control", "no-cache; no-store");
        HttpSession session = req.getSession();
        int accountid = 0;
        if (session != null && session.getAttribute("accountID") != null) accountid = (Integer) session.getAttribute("accountID");
        HTTPWrapper wrapper = new HTTPWrapper((HttpServletResponse) response);
        chain.doFilter(request, wrapper);
        String responseString = new String(wrapper.toString());
        if (accountid != 0) {
            responseString = responseString.replaceAll("previousRestaurantName", DBAccess.getAccountInfoBlock(accountid).username);
            responseString = responseString.replaceAll("previousRestaurantPhone", DBAccess.getAccountInfoBlock(accountid).phoneNum);
            responseString = responseString.replaceAll("previousRestaurantEmail", DBAccess.getAccountInfoBlock(accountid).emailAddress);
            responseString = responseString.replaceAll("previousRestaurantPass", DBAccess.getAccountInfoBlock(accountid).password);
            if (DBAccess.getAccountInfoBlock(accountid).accountType == 1) {
                responseString = responseString.replaceAll("previousRestaurantAddress", DBAccess.getRestaurantInfoBlock(accountid).address);
                responseString = responseString.replaceAll("previous2", Integer.toString(DBAccess.getTableInfoBlock(DBAccess.getRestaurantInfoBlock(accountid).restid).twoseat));
                responseString = responseString.replaceAll("previous4", Integer.toString(DBAccess.getTableInfoBlock(DBAccess.getRestaurantInfoBlock(accountid).restid).fourseat));
                responseString = responseString.replaceAll("previous6", Integer.toString(DBAccess.getTableInfoBlock(DBAccess.getRestaurantInfoBlock(accountid).restid).sixseat));
                responseString = responseString.replaceAll("previous8", Integer.toString(DBAccess.getTableInfoBlock(DBAccess.getRestaurantInfoBlock(accountid).restid).eightseat));
                responseString = responseString.replaceAll("previous12", Integer.toString(DBAccess.getTableInfoBlock(DBAccess.getRestaurantInfoBlock(accountid).restid).twelveseat));
            }
        }
        response.setContentLength(responseString.length());
        out.write(responseString);
        out.close();
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
