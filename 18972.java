import nitf.*;

/**
 * This test outputs the data from the NITF record to System.out
 */
public class TestReader2 {

    public static void main(String[] args) throws NITFException {
        if (args.length != 1) {
            System.out.println("Usage: java TestReader2 <nitf-file>");
            System.exit(0);
        }
        System.out.println("Creating reader...");
        Reader reader = new Reader();
        System.out.println("Setting up handle...");
        IOHandle handle = new IOHandle(args[0], IOHandle.NITF_ACCESS_READONLY, IOHandle.NITF_OPEN_EXISTING);
        System.out.println("Parsing NITF file...");
        Record record = reader.read(handle);
        record.print(System.out);
        TextSegment[] texts = record.getTexts();
        for (int i = 0; i < texts.length; i++) {
            TextSegment segment = texts[i];
            SegmentReader textReader = reader.getNewTextReader(i);
            byte[] data = new byte[(int) textReader.getSize()];
            textReader.read(data);
            System.out.println("DATA = '" + new String(data) + "'");
        }
        handle.close();
    }
}
