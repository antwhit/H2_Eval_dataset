import java.security.*;
import javax.crypto.*;

public class HMAC {

    public static byte[] doHMAC(Object O, SecretKey hmackey) {
        try {
            ByteConverter forHmac = new ByteConverter();
            byte[] objectByteArray = forHmac.toByteArray(O);
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(hmackey);
            byte[] completed = mac.doFinal(objectByteArray);
            return completed;
        } catch (Exception e) {
            System.out.println("Ivalid key for HMAC");
            return null;
        }
    }

    public static boolean checkHMAC(byte[] signed, Object O, SecretKey hmackey) {
        try {
            ByteConverter forCheck = new ByteConverter();
            byte[] objectByteArrayCheck = forCheck.toByteArray(O);
            Mac macCheck = Mac.getInstance("HmacSHA1");
            macCheck.init(hmackey);
            byte[] checked = macCheck.doFinal(objectByteArrayCheck);
            if (checked == signed) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ivalid key for HMAC");
            return false;
        }
    }
}
