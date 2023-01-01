import java.util.Date;
import org.quickfix.*;
import org.quickfix.field.*;

public class Application extends MessageCracker implements org.quickfix.Application {

    public Application() {
    }

    public void onCreate(SessionID sessionID) {
    }

    public void onLogon(SessionID sessionID) {
    }

    public void onLogout(SessionID sessionID) {
    }

    public void toAdmin(org.quickfix.Message message, SessionID sessionID) {
    }

    public void toApp(org.quickfix.Message message, SessionID sessionID) throws DoNotSend {
    }

    public void fromAdmin(org.quickfix.Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    public void fromApp(org.quickfix.Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID);
    }

    public void onMessage(org.quickfix.fix42.NewOrderSingle order, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        Symbol symbol = new Symbol();
        Side side = new Side();
        OrdType ordType = new OrdType();
        OrderQty orderQty = new OrderQty();
        Price price = new Price();
        ClOrdID clOrdID = new ClOrdID();
        order.get(ordType);
        if (ordType.getValue() != OrdType.LIMIT) throw new IncorrectTagValue(ordType.getField());
        order.get(symbol);
        order.get(side);
        order.get(orderQty);
        order.get(price);
        order.get(clOrdID);
        org.quickfix.fix42.ExecutionReport executionReport = new org.quickfix.fix42.ExecutionReport(genOrderID(), genExecID(), new ExecTransType('0'), new ExecType(ExecType.NEW), new OrdStatus(OrdStatus.NEW), symbol, side, new LeavesQty(0), new CumQty(orderQty.getValue()), new AvgPx(price.getValue()));
        executionReport.set(clOrdID);
        executionReport.set(orderQty);
        executionReport.set(new LastShares(orderQty.getValue()));
        executionReport.set(new LastPx(price.getValue()));
        try {
            Session.sendToTarget(executionReport, sessionID);
        } catch (SessionNotFound e) {
        }
    }

    public void onRun() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public OrderID genOrderID() {
        return new OrderID(new Integer(++m_orderID).toString());
    }

    public ExecID genExecID() {
        return new ExecID(new Integer(++m_execID).toString());
    }

    private int m_orderID = 0;

    private int m_execID = 0;
}
