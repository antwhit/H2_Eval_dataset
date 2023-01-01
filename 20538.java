import com.hexiong.jdbf.DBFReader;
import java.io.PrintStream;
import java.net.URL;

public class Test {

    public Test() {
    }

    public static void main(String args[]) throws Exception {
        DBFReader dbfreader = new DBFReader("E:\\hexiong\\work\\project\\tablemeta.dbf");
        int i;
        for (i = 0; i < dbfreader.getFieldCount(); i++) {
            System.out.print(dbfreader.getField(i).getName() + "  ");
        }
        System.out.print("\n");
        for (i = 0; dbfreader.hasNextRecord(); i++) {
            Object aobj[] = dbfreader.nextRecord();
            for (int j = 0; j < aobj.length; j++) System.out.print(aobj[j] + "  |  ");
            System.out.print("\n");
        }
        System.out.println("Total Count: " + i);
    }
}
