import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.InetAddress;
import net.nutss.STUNTSelector;
import net.nutss.stunt.STUNTServerSocket;
import net.nutss.stunt.STUNTSocket;

public class STUNTCheckServer {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        InetSocketAddress srvaddr = new InetSocketAddress("84.234.17.41", 12345);
        STUNTSelector sel = new STUNTSelector();
        STUNTServerSocket psock = null;
        try {
            psock = STUNTChecker.getNBLstnSock(srvaddr, sel);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            if (!psock.hasData()) try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                return;
            }
            try {
                STUNTSocket asock = psock.accept();
                if (asock == null) continue;
                InetAddress iaddr = null;
                iaddr = asock.s.getInetAddress();
                int i = 0;
                System.out.println("an input:" + iaddr.toString());
                while (i < 200 && !asock.hasData()) {
                    ++i;
                    Thread.sleep(100);
                }
                if (!asock.hasData()) {
                    asock.close();
                    continue;
                }
                int pport = asock.readInt();
                asock.close();
                Thread.sleep(1000);
                InetSocketAddress bindaddr = new InetSocketAddress("84.234.17.41", 12346);
                System.out.print(iaddr.getHostAddress() + ":");
                System.out.println(pport);
                asock = STUNTChecker.getNBConnSock(bindaddr, new InetSocketAddress(iaddr.getHostAddress(), pport), null, true);
                i = 0;
                while (i < 19 && !asock.hasData()) {
                    ++i;
                    Thread.sleep(500);
                }
                if (asock.hasData()) {
                    System.out.println("success");
                }
                asock.close();
                System.out.print(iaddr.getHostAddress() + ":");
                System.out.println(pport);
                asock = STUNTChecker.getNBConnSock(bindaddr, new InetSocketAddress(iaddr.getHostAddress(), pport), null, true);
                i = 0;
                while (i < 20 && !asock.hasData()) {
                    ++i;
                    Thread.sleep(500);
                }
                if (asock.hasData()) {
                    System.out.println("success");
                }
                asock.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
