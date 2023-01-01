import java.security.cert.Certificate;
import java.security.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

class ExportPriv {

    public static void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java ExportPriv <keystore> <alias> <password>");
            System.exit(1);
        }
        ExportPriv myep = new ExportPriv();
        myep.doit(args[0], args[1], args[2]);
    }

    public void doit(String fileName, String aliasName, String pass) throws Exception {
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] passPhrase = pass.toCharArray();
        File certificateFile = new File(fileName);
        ks.load(new FileInputStream(certificateFile), passPhrase);
        KeyPair kp = getPrivateKey(ks, aliasName, passPhrase);
        PrivateKey privKey = kp.getPrivate();
        char[] b64 = Base64Coder.encode(privKey.getEncoded());
        System.out.println("-----BEGIN PRIVATE KEY-----");
        for (String subSeq : splitArray(b64, 64)) {
            System.out.println(subSeq.toCharArray());
        }
        System.out.println("-----END PRIVATE KEY-----");
    }

    public KeyPair getPrivateKey(KeyStore keystore, String alias, char[] password) {
        try {
            Key key = keystore.getKey(alias, password);
            if (key instanceof PrivateKey) {
                Certificate cert = keystore.getCertificate(alias);
                PublicKey publicKey = cert.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        } catch (UnrecoverableKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (KeyStoreException e) {
        }
        return null;
    }

    private Vector<String> splitArray(char[] chry, int subarrLen) {
        Vector<String> result = new Vector<String>();
        String input = new String(chry);
        int i = 0;
        while (i < chry.length) {
            result.add(input.substring(i, Math.min(input.length(), i + subarrLen)));
            i = i + subarrLen;
        }
        return result;
    }
}
