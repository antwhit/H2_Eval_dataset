import java.io.*;
import java.security.*;
import sun.security.x509.*;
import sun.security.util.*;

class Sign {

    public static void main(String arg[]) {
        try {
            FileInputStream fisk = new FileInputStream("CAStore");
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");
            String storepass = "CertAuth";
            char[] pwd = new char[storepass.length()];
            for (int i = 0; i < pwd.length; i++) pwd[i] = storepass.charAt(i);
            String keypass = "openxrs";
            char[] kpwd = new char[keypass.length()];
            for (int i = 0; i < kpwd.length; i++) kpwd[i] = keypass.charAt(i);
            ks.load(fisk, pwd);
            PrivateKey priv = (PrivateKey) ks.getKey("CAKey", kpwd);
            X509Cert certs = new X509Cert();
            certs.decode(new FileInputStream("CAcert.crt"));
            AlgorithmId SHAalg = new AlgorithmId(AlgorithmId.DSA_oid);
            X500Signer CA = certs.getSigner(SHAalg, priv);
            FileInputStream bis = new FileInputStream("Servercert.crt");
            X509Cert srvrCert = new X509Cert();
            srvrCert.decode(bis);
            BigInt i = srvrCert.getSerialNumber();
            byte[] signedcert = srvrCert.encodeAndSign(i, CA);
            FileOutputStream certfos = new FileOutputStream("signedcert.crt");
            certfos.write(signedcert);
            certfos.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
