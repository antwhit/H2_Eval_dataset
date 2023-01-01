import java.util.*;
import java.text.*;

public class DictionaryRecord {

    private String lang1;

    private String lang2;

    private int lvl;

    private Date date;

    private int occurence;

    private int correct;

    private boolean seenThisLesson;

    private static final long serialVersionUID = 2L;

    public void reset() {
        lvl = 0;
        occurence = 0;
        correct = 0;
        seenThisLesson = false;
        this.date = new Date();
    }

    public DictionaryRecord(String l1, String l2, int lvl, Date date, int occurence, int correct) {
        lang1 = l1;
        lang2 = l2;
        this.lvl = lvl;
        this.date = date;
        this.occurence = occurence;
        this.correct = correct;
        seenThisLesson = false;
    }

    public DictionaryRecord() {
        this("", "", 0, new Date(), 0, 0);
    }

    public void resetSeen() {
        seenThisLesson = false;
    }

    public boolean getSeen() {
        return seenThisLesson;
    }

    public void seen() {
        seenThisLesson = true;
        date = new Date();
    }

    public String getLang1() {
        Dictionary dict = Dictionary.getDictionary();
        if (dict.getSwitchLanguages()) return lang2;
        return lang1;
    }

    public String getLang2() {
        Dictionary dict = Dictionary.getDictionary();
        if (dict.getSwitchLanguages()) return lang1;
        return lang2;
    }

    public void setLang1(String s) {
        Dictionary dict = Dictionary.getDictionary();
        if (dict.getSwitchLanguages()) lang2 = s; else lang1 = s;
    }

    public void setLang2(String s) {
        Dictionary dict = Dictionary.getDictionary();
        if (dict.getSwitchLanguages()) lang1 = s; else lang2 = s;
    }

    public int getLevel() {
        return lvl;
    }

    public void setLevel(int i) {
        lvl = i;
    }

    public int getOccurence() {
        return occurence;
    }

    public void setOccurence(int occ) {
        occurence = occ;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int c) {
        correct = c;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public String getWriteOut() {
        if (getLang1().equals("") || getLang2().equals("")) return "";
        String elem = getLang1() + " # " + getLang2() + " # " + getLevel();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        elem += " # " + dateFormat.format(getDate()) + " # " + getCorrect() + " # " + getOccurence();
        return elem;
    }
}
