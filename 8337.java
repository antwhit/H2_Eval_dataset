import edu.ucdavis.genomics.metabolomics.binbase.cluster.handler.AbstractClusterHandler;
import edu.ucdavis.genomics.metabolomics.util.status.ReportEvent;
import edu.ucdavis.genomics.metabolomics.util.status.ReportType;

public class YourOwnCalculatio2 extends AbstractClusterHandler {

    /**
	 * the start method of a cluster handler
	 * @author wohlgemuth
	 * @version Jul 28, 2006
	 * @see edu.ucdavis.genomics.metabolomics.binbase.cluster.ClusterHandler#start()
	 */
    public boolean startProcessing() throws Exception {
        getReport().report(getObject().toString(), new ReportEvent("i start", "i start my calculation"), new ReportType("my own type", "no description"));
        System.out.println("here I am and thats my receveid object for configuration: " + this.getObject());
        getReport().report(getObject().toString(), new ReportEvent("i finished", "i finished my calculation"), new ReportType("my own type", "no description"));
        return true;
    }
}
