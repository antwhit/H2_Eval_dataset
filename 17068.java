import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.TestCase;

public class BackupParserTest extends TestCase implements BackupRecordHandler {

    private int contactCount = 0;

    private int eventCount = 0;

    private int todoCount = 0;

    public void testParse() throws FileNotFoundException, IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("Backup-Nokia6300-090501.txt"));
        BackupParser parser = new BackupParser();
        System.out.println("#### PARSING STARTED ####");
        parser.parse(dis, this);
        System.out.println("#### PARSING ENDED ####");
        assertEquals("contactCount", 64, contactCount);
        assertEquals("eventCount", 40, eventCount);
        assertEquals("todoCount", 1, todoCount);
    }

    public void handleRecord(int recordType, String recordSubTypeName, String record) {
        assertNotNull(record);
        switch(recordType) {
            case BackupRecordHandler.TYPE_CONTACT:
                ++contactCount;
                break;
            case BackupRecordHandler.TYPE_EVENT:
                ++eventCount;
                break;
            case BackupRecordHandler.TYPE_TODO:
                ++todoCount;
                break;
            default:
                fail("Unknown record type: " + recordType);
        }
        assertNotNull(recordSubTypeName);
        if (recordType == BackupRecordHandler.TYPE_CONTACT) {
            assertTrue("Shall begin with BEGIN:VCARD", record.startsWith("BEGIN:VCARD"));
            assertTrue("Shall end with END:VCARD", record.endsWith("END:VCARD"));
        }
        System.out.println("#### handleRecord: type=" + recordType + ", subtype '" + recordSubTypeName + "', body=\n" + record);
    }
}
