import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class XAwareQueueTestBean implements MessageDrivenBean, MessageListener {

    private static String JMS_FACTORY = "";

    private static String QUEUE_RESPONSE = "";

    private transient MessageDrivenContext mdc;

    private QueueSender queueSender;

    private Queue sendQueue;

    private QueueConnection senderConnection;

    private QueueConnectionFactory senderFactory;

    private QueueSession senderSession;

    public XAwareQueueTestBean() {
        mdc = null;
    }

    public void ejbCreate() {
        try {
            InitialContext ctx = new InitialContext();
            QUEUE_RESPONSE = (String) ctx.lookup("java:comp/env/QueueResponse");
            sendQueue = (Queue) ctx.lookup(QUEUE_RESPONSE);
            queueSender = senderSession.createSender(sendQueue);
            JMS_FACTORY = (String) ctx.lookup("java:comp/env/JmsFactory");
            senderFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
            senderConnection = senderFactory.createQueueConnection();
            senderSession = senderConnection.createQueueSession(false, 1);
            senderConnection.start();
            StringBuffer msgBuf = new StringBuffer();
            msgBuf.append("In XAwareQueueTestBean.ejbCreate(),\n").append("\tJMS_FACTORY: ").append(JMS_FACTORY).append("\n").append("\tsenderFactory: ").append(senderFactory).append("\n").append("\tsenderConnection: ").append(senderConnection).append("\n").append("\tsenderSession: ").append(senderSession).append("\n").append("\tQUEUE_RESPONSE: ").append(QUEUE_RESPONSE).append("\n").append("\tsendQueue: ").append(sendQueue).append("\n").append("\tqueueSender: ").append(queueSender);
            System.out.println(msgBuf);
        } catch (NamingException e) {
            System.out.println("ejbCreate:Naming exception " + e.getMessage());
        } catch (JMSException e) {
            System.out.println("ejbCreate:JMS exception " + e.getMessage());
        }
    }

    public void onMessage(Message inMessage) {
        try {
            System.out.println("XAwareQueueTestBean.onMessage::Messageid:" + inMessage.getJMSMessageID());
            if (inMessage instanceof TextMessage) {
                String msgText = ((TextMessage) inMessage).getText();
                System.out.println("Message:" + msgText);
            }
            Thread.sleep(2000L);
            send(inMessage, inMessage.getJMSMessageID());
        } catch (Exception e) {
            String className = e.getClass().getName();
            System.out.println("ERROR: " + className + " exception in XAwareQueueTestBean.onMessage: " + e.getMessage());
        }
    }

    private void send(Message msgToSend, String id) throws JMSException {
        System.out.println("XAwareQueueTestBean.send::MsgId:" + id);
        msgToSend.setJMSCorrelationID(id);
        queueSender.send(msgToSend);
        System.out.println("XAwareQueueTestBean.send:: message sent.");
    }

    public void setMessageDrivenContext(MessageDrivenContext mdc) {
        this.mdc = mdc;
    }

    public void ejbRemove() {
    }
}
