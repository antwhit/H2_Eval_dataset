import java.util.*;

class TransMemory {

    public TransMemory(String src, String tar, String f) {
        source = src;
        target = tar;
        file = f;
    }

    public String source;

    public String target;

    public String file;
}

class NearString implements Comparable {

    public NearString(StringEntry strEntry, double nearScore, byte[] parData, byte[] nearData, String projName) {
        int i;
        str = strEntry;
        score = nearScore;
        parAttr = parData;
        attr = nearData;
        if (projName != null) proj = projName;
    }

    public int compareTo(Object obj) {
        NearString visitor = (NearString) obj;
        Double homeScore = new Double(score);
        Double visitorScore = new Double(visitor.score);
        return homeScore.compareTo(visitorScore);
    }

    public StringEntry str;

    public double score;

    public byte[] parAttr;

    public byte[] attr;

    public String proj = "";
}
