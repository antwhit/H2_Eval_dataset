import java.lang.System;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class OlahFile {

    String hslLoad;

    public OlahFile() {
    }

    public void buatFile(String s, String data) throws IOException {
        FileOutputStream tulis = new FileOutputStream(s);
        for (int i = 0; i < data.length(); i++) tulis.write(data.charAt(i));
        tulis.close();
    }

    public void bacaFile(String s) throws IOException {
        FileInputStream baca = new FileInputStream(s);
        int intByte = baca.available();
        byte intBuf[] = new byte[intByte];
        int intRead = baca.read(intBuf, 0, intByte);
        hslLoad = new String(intBuf, 0);
        baca.close();
    }

    public void hapusFile(String s) throws IOException {
        File hapus = new File(s);
        hapus.delete();
    }
}
