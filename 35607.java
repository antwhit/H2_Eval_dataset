import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created: 24 Feb 2006
 * @author <a href="mailto:dwayne@schultz.net">Dwayne Schultz</a>
 * @version $Revision: 150 $, $Date: 2008-06-21 16:42:46 -0400 (Sat, 21 Jun 2008) $
 */
public class SimpleProduceConsume {

    public static void main(final String[] args) throws NamingException, JMSException {
        if (args.length != 1) {
            usage();
        } else if (args[0] != null && args[0].equals("produce")) {
            produce();
        } else if (args[0] != null && args[0].equals("consume")) {
            consume();
        }
    }

    private static InitialContext getInitialContext() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "net.sf.dropboxmq.jndi.InitialContextFactoryImpl");
        properties.setProperty("net.sf.dropboxmq.root", "/dropboxmq");
        InitialContext initialContext = new InitialContext(properties);
        return initialContext;
    }

    private static void produce() throws NamingException, JMSException {
        InitialContext initialContext = getInitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
        Destination destination = (Destination) initialContext.lookup("TestQueue1");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("Hello World!");
        producer.send(message);
    }

    private static void consume() throws NamingException, JMSException {
        InitialContext initialContext = getInitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("ConnectionFactory");
        Destination destination = (Destination) initialContext.lookup("TestQueue1");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        MessageConsumer consumer = session.createConsumer(destination);
        TextMessage message = (TextMessage) consumer.receiveNoWait();
        System.out.println(message == null ? "No message found" : message.getText());
    }

    private static void usage() {
        System.out.println("usage:");
        System.out.println("   java SimpleProduceConsume {produce|consume}");
    }
}
