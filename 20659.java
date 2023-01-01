import edu.ucdavis.genomics.metabolomics.binbase.cluster.handler.AbstractClusterHandler;

/**
 * calculates PI for us
 * @author wohlgemuth
 *
 */
public class ExamplePi extends AbstractClusterHandler {

    public boolean startProcessing() throws Exception {
        System.out.println(Math.PI);
        return true;
    }
}
