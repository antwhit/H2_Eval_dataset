import java.util.ArrayList;

public class Legend {

    ArrayList<ArrayList<String[]>> legendEntries;

    public Legend() {
        legendEntries = new ArrayList<ArrayList<String[]>>();
        for (int i = 0; i < 4; i++) {
            legendEntries.add(new ArrayList<String[]>());
        }
    }

    public void addLegendEntry(int index, String key, String item) {
        String[] entry = new String[2];
        entry[0] = key;
        entry[1] = item;
        legendEntries.get(index).add(entry);
    }

    public void clearLegend() {
        for (int i = 0; i < 4; i++) {
            legendEntries.get(i).clear();
        }
    }

    public ArrayList<ArrayList<String[]>> getLegendEntries() {
        return legendEntries;
    }
}
