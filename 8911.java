public class Word extends Syntax {

    private Syllable[] syllables;

    public Word(Syllable[] syllables) {
        this.syllables = syllables;
        readable = new ReadableWord(this);
    }

    @Override
    public String toString() {
        String result = "";
        for (Syllable s : syllables) result += s.toString();
        return result;
    }

    public Syllable[] getSyllables() {
        return syllables;
    }
}
