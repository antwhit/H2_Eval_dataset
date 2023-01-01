import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Alignment {

    List<Sequence> sequences;

    public Alignment() {
        sequences = new ArrayList<Sequence>();
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void addSequence(Sequence s) {
        sequences.add(s);
    }

    public Sequence getSequence(int index) {
        return sequences.get(index);
    }

    public Sequence getSequence(String name) {
        for (Sequence seq : sequences) {
            if (name.equals(seq.getName())) {
                return seq;
            }
        }
        return null;
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (Sequence seq : sequences) {
            names.add(seq.getName());
        }
        return names;
    }

    public String getSequenceName(int i) {
        return sequences.get(i).getName();
    }

    public int numSequences() {
        return sequences.size();
    }

    public int maxLength() {
        int max = 0;
        for (Sequence seq : sequences) {
            if (seq.size() > max) {
                max = seq.size();
            }
        }
        return max;
    }

    public String toString() {
        StringBuffer output = new StringBuffer();
        for (Sequence seq : sequences) {
            output.append(seq.getName());
            output.append(System.getProperty("line.separator"));
            output.append(seq.getSequence());
            output.append(System.getProperty("line.separator"));
        }
        return output.toString();
    }
}
