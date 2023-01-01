import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * The XmlRpcController class  contais all the methods that handle xml-rpc calls.
 * 
 */
public class XmlRpcController {

    private String server = null;

    /**
     * Creates an instance of XmlRpcController
     * 
     * @param server the url where the server.php script is located
     * 
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public XmlRpcController(String server) throws NoSuchAlgorithmException, KeyManagementException {
        this.server = server;
    }

    /**
     * Executes a XML-RPC call to retrieve the latest client versionnumber
     *
     * @return String containing the latest client versionnumber
     * @throws java.net.MalformedURLException
     * @throws org.apache.xmlrpc.XmlRpcException
     */
    public String getLatestVersion() throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(this.server));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] params = new Object[] {};
        String version = (String) client.execute("getLatestVersion", params);
        return version;
    }

    /**
     * Executes a XML-RPC call to check if the given username and password return a valid
     * user in the database.
     * 
     * @param username String with the username
     * @param password String with the passwordhash
     * @return boolean depending if a user with that username/password combination is found
     * 
     * @throws java.net.MalformedURLException
     * @throws org.apache.xmlrpc.XmlRpcException
     */
    public boolean checkUser(String username, String password) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(this.server));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] params = new Object[] { username, password };
        boolean passed = (Boolean) client.execute("checkUser", params);
        return passed;
    }

    /**
     * Executes a XML-RPC call that will write the testresult into the database
     * if the userdata is valid.
     * 
     * @param resultMap HashMap containing the userdata and a testresult
     * @throws org.apache.xmlrpc.XmlRpcException
     * @throws java.net.MalformedURLException
     */
    public void storeResult(HashMap resultMap) throws XmlRpcException, MalformedURLException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(this.server));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] params = new Object[] { resultMap };
        client.execute("storeResults", params);
    }
}
