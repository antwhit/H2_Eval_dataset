import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import javax.crypto.NoSuchPaddingException;

public class cPhoxyPublicFile {

    private File PhoxyPublicFile;

    private String FileInformation;

    private cSymmCrypto KeyPassword;

    public cPhoxyPublicFile(String FileInformation, String Password, String HashAlgo, String CrypAlgo) throws NoSuchAlgorithmException {
        this.FileInformation = FileInformation;
        this.generateKeyPassword(Password, HashAlgo, CrypAlgo);
    }

    public cPhoxyPublicFile(String Password, String HashAlgo, String CrypAlgo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        this.generateKeyPassword(Password, HashAlgo, CrypAlgo);
    }

    public void getPhoxyPublicFile(String DstPath) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        this.PhoxyPublicFile = new File(DstPath);
        DataOutputStream DataOutputStream = new DataOutputStream(this.KeyPassword.getCipherOutputStream(new FileOutputStream(this.PhoxyPublicFile)));
        DataOutputStream.writeBytes(this.FileInformation);
        DataOutputStream.flush();
        DataOutputStream.close();
    }

    public cPublicFile getPublicFileInstance(String SrcPath, String HashAlgo, String CrypAlgo, String SplitNameHashAlgo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        this.PhoxyPublicFile = new File(SrcPath);
        BufferedReader BufferedReader = new BufferedReader(new InputStreamReader(this.KeyPassword.getCipherInputStream(new FileInputStream(this.PhoxyPublicFile))));
        String Buffer = BufferedReader.readLine();
        StringTokenizer tok = new StringTokenizer(Buffer, ":;");
        tok.nextToken();
        String Author = tok.nextToken();
        tok.nextToken();
        String Titel = tok.nextToken();
        tok.nextToken();
        String FileName = tok.nextToken();
        tok.nextToken();
        long Size = Long.parseLong(tok.nextToken());
        tok.nextToken();
        String NetworkName = tok.nextToken();
        tok.nextToken();
        String Beschreibung = tok.nextToken();
        return new cPublicFile(FileName, Titel, Author, Size, NetworkName, Beschreibung, HashAlgo, CrypAlgo, SplitNameHashAlgo);
    }

    public void generateKeyPassword(String Password, String HashAlgo, String CrypAlgo) throws NoSuchAlgorithmException {
        String KeyPassword = Password + "Ist ein Geheimnis";
        this.KeyPassword = new cSymmCrypto(KeyPassword, HashAlgo, CrypAlgo);
    }
}
