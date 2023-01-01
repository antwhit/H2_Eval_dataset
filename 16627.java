import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class NetflixDataPrepDriver {

    public static void main(String[] args) {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(NetflixDataPrepDriver.class);
        conf.setJobName("NetflixDataPrep");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);
        conf.setNumReduceTasks(80);
        conf.setInputFormat(org.apache.hadoop.mapred.TextInputFormat.class);
        conf.setOutputFormat(org.apache.hadoop.mapred.SequenceFileOutputFormat.class);
        if (args.length < 2) {
            System.out.println("Usage: NetflixDataPrep <input path> <output path>");
            System.exit(0);
        }
        conf.setInputPath(new Path(args[0]));
        conf.setOutputPath(new Path(args[1]));
        conf.setMapperClass(NetflixDataPrepMapper.class);
        conf.setReducerClass(NetflixDataPrepReducer.class);
        client.setConf(conf);
        try {
            JobClient.runJob(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
