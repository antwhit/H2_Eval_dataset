import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Results {

    class Record {

        private final int[] roundtrip_times;

        public Record(TestType type) {
            roundtrip_times = new int[type.getAmount()];
            Arrays.fill(roundtrip_times, -1);
        }

        public int[] getRoundtripTimes() {
            return roundtrip_times;
        }

        public void set(int index, int roundtrip) {
            roundtrip_times[index] = roundtrip;
        }
    }

    private final EnumMap<TestType, LinkedHashMap<String, Record>> results;

    public Results() {
        results = new EnumMap<TestType, LinkedHashMap<String, Record>>(TestType.class);
    }

    public void add(TestType type, String testid, int index, int roundtrip) {
        LinkedHashMap<String, Record> records = results.get(type);
        if (records == null) {
            records = new LinkedHashMap<String, Record>(10, 0.5f);
            results.put(type, records);
        }
        Record record = records.get(testid);
        if (record == null) {
            record = new Record(type);
            records.put(testid, record);
        }
        record.set(index, roundtrip);
    }

    public void toCSVWriter(Writer writer) throws IOException {
        BufferedWriter buffered_writer = new BufferedWriter(writer, 50 * 512);
        for (Map.Entry<TestType, LinkedHashMap<String, Record>> results_entry : results.entrySet()) {
            buffered_writer.write("Tests with: " + results_entry.getKey());
            buffered_writer.newLine();
            buffered_writer.write("Stats");
            int count = results_entry.getKey().getAmount();
            for (int i = 0; i < count; i++) {
                buffered_writer.write(";");
                buffered_writer.write(String.valueOf(i));
            }
            buffered_writer.newLine();
            for (Map.Entry<String, Record> records_entry : results_entry.getValue().entrySet()) {
                buffered_writer.write(records_entry.getKey());
                final int[] rtt = records_entry.getValue().getRoundtripTimes();
                for (int j = 0; j < rtt.length; j++) {
                    buffered_writer.write(";");
                    buffered_writer.write(String.valueOf(rtt[j]));
                }
                buffered_writer.newLine();
            }
            buffered_writer.newLine();
        }
        buffered_writer.flush();
        System.out.println("Finished writing!");
    }
}
