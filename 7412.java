import java.net.URI;
import java.util.Properties;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPTester {

    public static void main(String[] args) {
        try {
            long ini = System.currentTimeMillis();
            Configuration conf = new Configuration();
            conf.set("fs.sftp.host", "localhost");
            conf.set("fs.sftp.user." + "localhost", "user");
            conf.set("fs.sftp.key", "/home/goirix/.ssh/id_rsa");
            conf.set("fs.sftp.impl", "org.apache.hadoop.fs.sftp.SFTPFileSystem");
            FileSystem fs = FileSystem.get(new URI("sftp://localhost"), conf);
            for (FileStatus file : fs.listStatus(new Path("/home/user"))) {
                System.out.println(" " + file.getPath());
            }
            fs.copyToLocalFile(new Path("/home/user/mola"), new Path("/home/goirix/testFileSFTP"));
            fs.copyFromLocalFile(new Path("/home/goirix/testFileSFTP"), new Path("/home/user/mola.upload"));
            System.out.println("Copied");
            for (FileStatus file : fs.listStatus(new Path("/home/user"))) {
                System.out.println(" " + file.getPath());
            }
            System.out.println("Time: " + (System.currentTimeMillis() - ini));
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
