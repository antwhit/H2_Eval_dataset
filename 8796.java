import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ResultParser {

    public LinkedHashMap<String, ArrayList<Result>> results;

    public ResultParser() {
        results = new LinkedHashMap<String, ArrayList<Result>>();
    }

    public void parse(String fileList[]) {
        results.clear();
        for (String fileName : fileList) {
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fileReader);
                reader.readLine();
                String algorithm = reader.readLine().trim();
                reader.readLine();
                reader.readLine();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    Result result = new Result();
                    result.algorithm = algorithm;
                    String parts[] = line.split(" ");
                    result.problem = parts[0];
                    result.solved = parts[1];
                    result.length = parts[2];
                    result.cost = parts[3];
                    result.nodesExpanded = parts[4];
                    result.msec = parts[5];
                    addResult(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addResult(Result result) {
        ArrayList<Result> list = null;
        list = results.get(result.problem);
        if (list == null) {
            list = new ArrayList<Result>();
            results.put(result.problem, list);
        }
        list.add(result);
    }

    public String latexOutput() {
        StringBuilder buf = new StringBuilder("Latex Output\n");
        for (String key : results.keySet()) {
            ArrayList<Result> list = results.get(key);
            for (Result result : list) {
                buf.append(result.problem).append(" & ").append(result.algorithm).append(" & ").append(result.solved).append(" & ").append(result.length).append(" & ").append(result.cost).append(" & ").append(result.nodesExpanded).append(" & ").append(result.msec).append("\\\\\n");
            }
            buf.append("\\hline\n");
        }
        return buf.toString();
    }

    /**
	 * @param args
	 * @throws IOException 
	 */
    public static void main(String[] args) throws IOException {
        ResultParser parser = new ResultParser();
        parser.parse(args);
        String output = parser.latexOutput();
        FileWriter writer = new FileWriter("latexoutput.txt");
        writer.write(output);
    }

    public class Result {

        public String problem;

        public String algorithm;

        public String solved;

        public String length;

        public String cost;

        public String nodesExpanded;

        public String msec;
    }
}
