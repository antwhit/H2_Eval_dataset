import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.log4j.Level;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.ClusterUtil;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.ClusterUtilFactory;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.handler.AbstractClusterHandler;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.node.CalculationNode;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.util.RocksClusterFactoryImpl;
import edu.ucdavis.genomics.metabolomics.binbase.cluster.util.Scheduler;
import edu.ucdavis.genomics.metabolomics.util.config.XMLConfigurator;
import edu.ucdavis.genomics.metabolomics.util.io.source.FileSource;
import edu.ucdavis.genomics.metabolomics.util.status.ReportEvent;
import edu.ucdavis.genomics.metabolomics.util.status.ReportType;
import edu.ucdavis.genomics.metabolomics.util.status.Reports;

/**
 * creates sub jobs to calculate fibuance codes using class:
 * @see YourOwnCalucation3
 * @author wohlgemuth
 *
 */
public class YourFirstSubJob extends AbstractClusterHandler {

    @Override
    protected boolean startProcessing() throws Exception {
        for (int i = 200; i < 250; i++) {
            getReport().report(getObject(), new ReportEvent("start", ""), new ReportType("create sub job", ""));
            this.startSubJob(new Long(i), YourOwnCalucation3.class.getName());
        }
        startLocalNode();
        waitForSubjobs();
        getReport().report(getObject(), Reports.DONE, Reports.JOB);
        return true;
    }

    public static void main(String[] args) throws Exception {
        org.apache.log4j.Logger.getLogger(YourFirstSubJob.class).setLevel(Level.DEBUG);
        XMLConfigurator.getInstance();
        XMLConfigurator.getInstance().addConfiguration(new FileSource(new File("config/applicationServer.xml")));
        Scheduler.schedule(new SubJobPropertie(), Scheduler.PRIORITY_NORMAL, YourFirstSubJob.class.getName());
        new CalculationNode().start();
    }
}

class SubJobPropertie implements Serializable {
}
