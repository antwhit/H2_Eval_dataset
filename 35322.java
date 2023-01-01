import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import edu.ucla.cs.typecast.cache2.CacheManager;
import edu.ucla.cs.typecast.cache2.CacheRequestor;
import edu.ucla.cs.typecast.net.EndPoint;
import edu.ucla.cs.typecast.net.TypeCastConstants;
import edu.ucla.cs.typecast.rmi.TypeCastServer;
import edu.ucla.cs.typecast.util.TypeUtil;

public class ContentServer {

    public static void main(String[] args) {
        try {
            Options options = new Options(args);
            int timeout = 60;
            String[] params = new String[0];
            Class rmiClass = Class.forName(args[0]);
            Object serviceObject = rmiClass.newInstance();
            TypeCastServer tserver = new TypeCastServer();
            Class[] types = TypeUtil.getInterfaces(rmiClass);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            EndPoint endPoint = new EndPoint(InetAddress.getByName(TypeCastConstants.DEFAULT_RMI_ADDRESS), TypeCastConstants.DEFAULT_RMI_PORT);
            while (true) {
                System.out.print("Enter return to request cache:");
                reader.readLine();
                CacheRequestor cacheRequestor = CacheManager.getInstance().getCacheRequestor(endPoint);
                cacheRequestor.requestCache(null, types, serviceObject, timeout);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
