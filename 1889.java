public class FullText extends Syntax {

    private Sentence[] sentences;

    public FullText(Sentence[] sentences) {
        this.sentences = sentences;
        readable = new ReadableFullText(this);
    }

    @Override
    public String toString() {
        String result = "";
        for (Sentence s : sentences) result += s.toString() + " ";
        return result;
    }

    public Sentence[] getSentences() {
        return sentences;
    }
}
