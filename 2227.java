import java.io.*;
import java.net.*;
import java.util.Map;

class SMTPHandler extends RequestHandler {

    private String from_address;

    private String to_address;

    private String message_data = null;

    String lineData;

    public enum StateEnum {

        AUTH, TRANS, UPDATE
    }

    ;

    StateEnum state;

    public SMTPHandler(Socket socket) {
        super(socket);
        state = StateEnum.AUTH;
    }

    public void setState(StateEnum s) {
        this.state = s;
    }

    public void doRequest() {
        try {
            String requestStr = readLineIn();
            if (isValidated()) {
                state = StateEnum.TRANS;
                if (requestStr.startsWith("MAIL FROM:")) {
                    from_address = requestStr.substring(requestStr.indexOf("<") + 1, requestStr.indexOf(">"));
                    printLineOut("250 " + from_address + " ... Sender ok");
                } else if (requestStr.startsWith("RCPT TO:")) {
                    to_address = requestStr.substring(requestStr.indexOf("<") + 1, requestStr.indexOf(">"));
                    printLineOut("250 " + from_address + " ... Recipient ok");
                } else if (requestStr.equals("DATA")) {
                    printLineOut("354 Enter mail, end with \".\" on a line by itself");
                    for (lineData = readLineIn(); !lineData.equals("."); lineData = readLineIn()) {
                        message_data = message_data + lineData;
                    }
                    printLineOut("250 Message accepted for delivery");
                } else if (requestStr.equals("QUIT")) {
                    printLineOut("221 supermail closing connection");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
