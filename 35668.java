import java.rmi.RemoteException;
import java.util.Enumeration;
import fi.hip.gb.core.JobResult;
import fi.hip.gb.core.WorkResult;
import fi.hip.gb.mobile.AgentApi;
import fi.hip.gb.mobile.Combiner;

/**
 * Combiner for agents which adds content of all results into one file.
 * If results are xml files, multiple instances of
 * <?xml version="1.0" encoding="UTF-8"?> tags are removed.
 *  
 * @author Juho Karppinen
 * @version $Id: AppenderCombiner.java 102 2004-11-12 14:31:37Z jkarppin $
 */
public class AppenderCombiner implements Combiner {

    public AppenderCombiner(AgentApi api) {
        _api = api;
        _content = new StringBuffer();
        _description = new StringBuffer();
        _description.append("Original description(s): ");
    }

    /**
	 * Append result data into string buffer
	 * 
	 * @see fi.hip.gb.mobile.Combiner#handleResult(fi.hip.gb.core.WorkResult)
	 */
    public WorkResult handleResult(WorkResult results) throws RemoteException {
        for (Enumeration e = results.results(); e.hasMoreElements(); ) {
            JobResult res = (JobResult) e.nextElement();
            String data = res.readContent();
            if (data != null) {
                _content.append(data).append("\n");
            }
            _description.append(res.getDescription()).append(",");
        }
        return null;
    }

    /**
	 * Returns all result data inside one result
	 * 
	 * @see fi.hip.gb.mobile.Combiner#getResults()
	 */
    public WorkResult getResults() throws RemoteException {
        WorkResult finalResults = new WorkResult(_api.getDescription().getServiceURL(), _api.getDescription().getJobID(), _api.getDirectory());
        finalResults.insertResult("appended", _description.toString(), _content.toString());
        return finalResults;
    }

    /** agent api */
    private AgentApi _api;

    /** content of all results */
    private StringBuffer _content;

    /** description of all results */
    private StringBuffer _description;
}
