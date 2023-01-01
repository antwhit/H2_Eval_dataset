import nitf.*;

/**
 * Tests Reading the DES data using the new DES UserSegment plugin(s)
 * This is a Java version of the C test case test_DES_read.c
 */
public class TestDESRead {

    public static void main(String[] args) throws NITFException {
        if (args.length != 1) {
            System.out.println("Usage: java TestDESRead <nitf-file>");
            System.exit(0);
        }
        System.out.println("Creating reader...");
        Reader reader = new Reader();
        System.out.println("Setting up handle...");
        IOHandle handle = new IOHandle(args[0], IOHandle.NITF_ACCESS_READONLY, IOHandle.NITF_OPEN_EXISTING);
        System.out.println("Parsing NITF file...");
        Record record = reader.read(handle);
        record.print(System.out);
        final DESegment[] dataExtensions = record.getDataExtensions();
        for (int i = 0; i < dataExtensions.length; i++) {
            DESegment dataExtension = dataExtensions[i];
            final DESubheader subheader = dataExtension.getSubheader();
            final TRE subheaderFields = subheader.getSubheaderFields();
            subheaderFields.print(System.out);
            final DEReader deReader = reader.getNewDEReader(i);
            final UserSegment userSegment = deReader.getUserSegment();
            System.out.println("userSegment.getDataLength() = " + userSegment.getDataLength());
            System.out.println("userSegment.getVirtualLength() = " + userSegment.getVirtualLength());
            System.out.println("userSegment.getBaseOffset() = " + userSegment.getBaseOffset());
            byte[] buf = new byte[userSegment.getVirtualLength()];
            final boolean status = deReader.read(buf, userSegment.getVirtualLength());
            System.out.println("status = " + status);
            System.out.println("buf = " + new String(buf));
        }
        handle.close();
    }
}
