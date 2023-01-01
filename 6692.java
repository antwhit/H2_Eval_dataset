import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import java.sql.*;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Willem Ligtenberg
 */
public class GetPathwayByNameTask implements Task {

    private String searchValue = "";

    private ArrayList species = null;

    private TaskMonitor taskMonitor = null;

    private boolean interrupted = false;

    /** Creates a new instance of GetPathwayByNameTask */
    public GetPathwayByNameTask(String searchValue, ArrayList species) {
        this.searchValue = searchValue;
        this.species = species;
    }

    public void run() {
        if (this.taskMonitor == null) {
            throw new IllegalStateException("Task Monitor is not set.");
        }
        taskMonitor.setPercentCompleted(-1);
        AbstractDatabaseConnect databaseConnect = (AbstractDatabaseConnect) new DatabaseConnect();
        databaseConnect.queryOnPathwayName(this.searchValue, this.species);
        taskMonitor.setStatus("Task is now complete.");
    }

    public void halt() {
        this.interrupted = true;
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) {
        if (this.taskMonitor != null) {
            throw new IllegalStateException("Task Monitor is already set.");
        }
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return new String("Get pathway by name");
    }
}
