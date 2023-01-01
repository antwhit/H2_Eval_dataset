import java.util.ArrayList;

public class DSoCTask {

    int total_hx_area;

    int total_sx_area;

    int deadLine;

    ArrayList<SES> sess;

    /**
	 * 
	 * @param SESs : an array of hardware/software small execution segments 
	 */
    public DSoCTask(ArrayList<SES> SESs) {
        sess = SESs;
    }
}
