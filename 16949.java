import java.util.ArrayList;

public class Master {

    private ArrayList<Job> jobs;

    private ArrayList<String> results;

    private int numberOfJobs;

    private int durationFactor;

    private int resultCounter = 1;

    private String result;

    public ArrayList<Job> getJobs() {
        return this.jobs;
    }

    ;

    public Master(int jobs, int durationFactor) {
        this.jobs = new ArrayList<Job>();
        this.results = new ArrayList<String>();
        this.numberOfJobs = jobs;
        this.durationFactor = durationFactor;
    }

    public static void main(final String[] args) {
        Master master = new Master(Integer.valueOf(args[0]).intValue(), Integer.valueOf(args[1]).intValue());
        master.start();
    }

    /**
	 * @param args
	 */
    public void start() {
        this.generateJobs();
        Long startTime = System.currentTimeMillis();
        while (!this.jobs.isEmpty() || resultCounter < numberOfJobs) {
            getResult();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }

    public void getResult() {
        synchronized (results) {
            while (!this.results.isEmpty()) {
                resultCounter++;
                result = results.remove(0);
            }
        }
    }

    private void generateJobs() {
        synchronized (jobs) {
            Job job = null;
            for (int i = 1; i <= numberOfJobs; i++) {
                job = new Job(i, ((i % 5) + 1) * durationFactor);
                this.jobs.add(job);
            }
        }
    }
}
