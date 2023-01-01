import javax.microedition.lcdui.*;

public class SendResult extends Form {

    public Command cmdSMS;

    public Command cmdBack;

    public TextField tfName, tfPhoneNo;

    public SendResult() {
        super("Send Callsign Info");
        StringItem si = new StringItem("Instruction", "Enter phone number to send the callsign info to");
        tfPhoneNo = new TextField("Phone Number ", "", 20, TextField.PHONENUMBER);
        this.append(si);
        this.append(tfPhoneNo);
    }
}
