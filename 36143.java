import java.net.Socket;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class SimpleClient {

    private Socket m_socket;

    private PrintStream m_os;

    private BufferedReader m_is;

    boolean m_trace;

    public SimpleClient() throws IOException {
        m_socket = new Socket("127.0.0.1", 4246);
        m_os = new PrintStream(m_socket.getOutputStream());
        m_is = new BufferedReader(new java.io.InputStreamReader(m_socket.getInputStream()));
        m_trace = true;
    }

    public Vector sendQuery(String q) throws IOException {
        m_os.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\" standalone=\"no\"?>");
        m_os.println("<!DOCTYPE cogitantquery SYSTEM cogitantcs.dtd>");
        m_os.println("<cogitantquery requiresheader=\"0\">");
        m_os.println("\t<" + q + "/>");
        m_os.println("</cogitantquery>");
        if (m_trace) System.out.println("--> " + q);
        return getAnswer();
    }

    public Vector getAnswer() throws IOException {
        Vector v = new Vector();
        String tmp;
        do {
            tmp = m_is.readLine();
            v.add(tmp);
        } while (!tmp.equals("</cogitantanswer>"));
        m_is.read();
        if (m_trace) {
            System.out.println("<-- " + v.get(0));
            for (int i = 1; i < v.size(); i++) System.out.println("    " + v.get(i));
        }
        return v;
    }

    public static void main(String args[]) throws IOException {
        SimpleClient sc = new SimpleClient();
        System.out.println("*** Querying the number of available environments on the server");
        sc.sendQuery("qserver");
        System.out.println("*** Creating a new environment");
        sc.sendQuery("qnewenvironment");
        int idenv = 0;
        System.out.println("*** Loading a support");
        sc.sendQuery("qloadsupport env=\"" + idenv + "\" file=\"bcgct/bucolic/bucolic.bcs\"");
        System.out.println("*** Loading a graph");
        sc.sendQuery("qloadgraphs env=\"" + idenv + "\" file=\"bcgct/bucolic/simplequery.bcg\"");
        int idgra = 0;
        System.out.println("*** Downloading this graph");
        sc.sendQuery("qenvironmentobject env=\"" + idenv + "\" id=\"" + idgra + "\"");
        System.out.println("*** Deleting the environment");
        sc.sendQuery("qdeleteenvironment env=\"0\"");
    }
}

;
