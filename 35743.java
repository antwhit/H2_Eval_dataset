import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author YUN TAO
 */
public class SendMail {

    public static final String EMAIL_VERI = "http://localhost:8080/peterhi-web/EmailVerification.jsp?s=";

    public static void send(Locale locale, String recipient, String subject, String body) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress("mail.peterhi@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            if (Locale.CHINA.equals(locale)) {
                String enc = MimeUtility.encodeText(subject, "GB2312", "B");
                msg.setSubject(enc);
                msg.setHeader(subject, "text/plain; charset=GB2312");
                msg.setContent(body, "text/plain; charset=GB2312");
            } else {
                msg.setSubject(subject);
                msg.setText(body);
            }
            msg.setSentDate(new Date());
            Transport t = session.getTransport("smtps");
            t.connect("smtp.gmail.com", "mail.peterhi@gmail.com", "xaero6139");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
