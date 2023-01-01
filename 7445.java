import nitf.DESegment;
import nitf.IOHandle;
import nitf.Record;
import nitf.SegmentSource;
import nitf.SegmentWriter;
import nitf.TRE;
import nitf.Writer;

public class TestDESCreate {

    static final String DATA = "123456789ABCDEF0";

    public static void main(String[] args) throws Exception {
        Record rec = new Record();
        rec.getHeader().getFileHeader().setData("NITF");
        rec.getHeader().getFileVersion().setData("02.10");
        rec.getHeader().getClassification().setData("U");
        rec.getHeader().getFileTitle().setData("Test DES");
        final DESegment des = rec.newDESegment();
        TRE newTRE = new TRE("TEST_DES");
        TRE tre = des.getSubheader().setSubheaderFields(newTRE);
        tre.setField("TEST_DES_COUNT", "16");
        tre.setField("TEST_DES_START", "065");
        tre.setField("TEST_DES_INCREMENT", "01");
        tre.print(System.out);
        des.getSubheader().getFilePartType().setData("DE");
        des.getSubheader().getTypeID().setData("TEST_DES");
        des.getSubheader().getVersion().setData("01");
        des.getSubheader().getSecurityClass().setData("U");
        IOHandle handle = new IOHandle("test.ntf", IOHandle.NITF_ACCESS_WRITEONLY, IOHandle.NITF_CREATE);
        Writer writer = new Writer();
        writer.prepare(rec, handle);
        final SegmentWriter deWriter = writer.getNewDEWriter(0);
        final SegmentSource source = SegmentSource.makeSegmentMemorySource(DATA.getBytes(), DATA.length(), 0, 0);
        deWriter.attachSource(source);
        writer.write();
    }
}
