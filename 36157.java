public class TopMatch implements Comparable {

    public String name;

    public double score;

    public double evalue;

    public int queryLength;

    public int targetLength;

    public int matchLength;

    public TopMatch(String n, String s, String e, String q, String t, String m) {
        this.name = n;
        this.score = Double.parseDouble(s);
        this.evalue = Double.parseDouble(e);
        this.queryLength = Integer.parseInt(q);
        this.targetLength = Integer.parseInt(t);
        this.matchLength = Integer.parseInt(m);
    }

    public int compareTo(Object o) {
        if (o instanceof TopMatch) {
            TopMatch t = (TopMatch) o;
            return (int) Math.rint(t.score * t.matchLength - this.score * this.matchLength);
        }
        return 0;
    }
}
