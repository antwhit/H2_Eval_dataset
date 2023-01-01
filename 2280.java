public class ReadableWord extends ReadableObject implements Readable {

    private Word word;

    public ReadableWord(Word word) {
        this.word = word;
    }

    public void read() {
        toFile(toMBROLA());
    }

    public String toMBROLA() {
        String result = "";
        for (Syllable s : word.getSyllables()) result += s.toMBROLA();
        return result;
    }
}
