import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.conf.Configuration;

public class test {

    private static final String NULL = null;

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("/home/jli351/hbase-0.90.4/conf/hbase-site.xml"));
        HBaseAdmin admin = new HBaseAdmin(config);
        HTable table = new HTable(config, "metrics");
        admin.disableTable("metrics");
        HColumnDescriptor cf1 = new HColumnDescriptor(Bytes.toBytes("info"));
        admin.addColumn("metrics", cf1);
        HColumnDescriptor cf2 = new HColumnDescriptor(Bytes.toBytes("powertag"));
        admin.addColumn("metrics", cf2);
        HColumnDescriptor cf3 = new HColumnDescriptor(Bytes.toBytes("vcavgusage"));
        admin.addColumn("metrics", cf3);
        HColumnDescriptor cf4 = new HColumnDescriptor(Bytes.toBytes("vcmachine"));
        admin.addColumn("metrics", cf4);
        admin.enableTable("metrics");
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        Put p = new Put(Bytes.toBytes("myLittleRow"));
        p.add(Bytes.toBytes("info"), Bytes.toBytes("someQualifier"), Bytes.toBytes("Some Value"));
        table.put(p);
        p = new Put(Bytes.toBytes("Row2"));
        p.add(Bytes.toBytes("info"), Bytes.toBytes("someQualifier"), Bytes.toBytes("Some Value"));
        table.put(p);
        Get g = new Get(Bytes.toBytes("myLittleRow"));
        Result r = table.get(g);
        byte[] value = r.getValue(Bytes.toBytes("info"), Bytes.toBytes("someQualifier"));
        String valueStr = Bytes.toString(value);
        System.out.println("GET: " + valueStr);
        Scan s = new Scan();
        s.addColumn(Bytes.toBytes("info"), Bytes.toBytes("someQualifier"));
        ResultScanner scanner = table.getScanner(s);
        try {
            for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
                System.out.println("Found row: " + rr);
            }
        } finally {
            scanner.close();
        }
        Delete d = new Delete(Bytes.toBytes("Row2"));
        table.delete(d);
        s = new Scan();
        s.addColumn(Bytes.toBytes("info"), Bytes.toBytes("someQualifier"));
        scanner = table.getScanner(s);
        try {
            for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
                System.out.println("Found row: " + rr);
            }
        } finally {
            scanner.close();
        }
    }
}
