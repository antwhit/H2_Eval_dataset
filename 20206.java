import com.chilkatsoft.CkMailMan;
import com.chilkatsoft.CkEmail;

public class EmailSimpleSend {

    static {
        try {
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        CkMailMan mailman = new CkMailMan();
        mailman.UnlockComponent("anything for 30-day trial");
        mailman.put_SmtpHost("smtp.comcast.net");
        CkEmail email = new CkEmail();
        email.put_Subject("Sending mail from Java");
        email.put_Body("This email was sent from a Java program");
        email.put_From("Chilkat Support <support@chilkatsoft.com>");
        email.AddTo("Matt", "matt@chilkatsoft.com");
        email.AddTo("TagTooga", "admin@tagtooga.com");
        boolean success = mailman.SendEmail(email);
        if (!success) {
            mailman.SaveLastError("lastError.txt");
        }
    }
}
