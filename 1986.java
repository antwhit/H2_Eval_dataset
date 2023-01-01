import javax.microedition.rms.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodStore {

    private RecordStore recordStore = null;

    public void openMethodStore() throws RecordStoreException {
        recordStore = recordStore.openRecordStore("methods", true);
    }

    public void closeMethodStore() throws RecordStoreException, RecordStoreNotOpenException {
        recordStore.closeRecordStore();
    }

    public int addMethod(String s) throws RecordStoreException, RecordStoreNotOpenException, RecordStoreFullException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(s);
        byte[] b = baos.toByteArray();
        int methodID = recordStore.addRecord(b, 0, b.length);
        return methodID;
    }

    public String getMethod(int methodID) throws RecordStoreException, InvalidRecordIDException, IOException {
        byte[] b = recordStore.getRecord(methodID);
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        String s = dis.readUTF();
        return s;
    }
}
