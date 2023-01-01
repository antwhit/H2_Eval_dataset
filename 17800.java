import com.chilkatsoft.CkMailMan;
import com.chilkatsoft.CkEmail;

public class SendChineseEmail {

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
        email.put_Subject("我能吞下玻璃而不伤身体");
        email.put_Body("我能吞下玻璃而不伤身体");
        email.put_From("我能 <support@chilkatsoft.com>");
        email.AddTo("下玻", "matt@chilkatsoft.com");
        email.AddTo("身体", "admin@tagtooga.com");
        boolean success = mailman.SendEmail(email);
        if (!success) {
            mailman.SaveLastError("lastError.txt");
        }
    }
}
