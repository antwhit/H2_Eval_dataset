import javax.swing.Icon;
import javax.swing.text.*;
import org.gjt.sp.jedit.Buffer;
import org.gjt.sp.util.Log;

/**
 * A data object containing the attributes of a formatted comment
 * contained in a source file, along with an Icon represeting the
 * type of task.
 * @author Oliver Rutherfurd
 */
public class Task {

    public Task(Buffer buffer, Icon icon, int line, String text, int startOffset, int endOffset) {
        this.buffer = buffer;
        this.icon = icon;
        this.lineIndex = line;
        this.text = text.replace('\t', (char) 187);
        int posOffset = buffer.getLineStartOffset(line);
        this.startPosition = buffer.createPosition(posOffset + startOffset);
        this.endPosition = buffer.createPosition(posOffset + endOffset);
    }

    public Buffer getBuffer() {
        return this.buffer;
    }

    public Icon getIcon() {
        return this.icon;
    }

    public String getText() {
        return this.text;
    }

    public int getLineIndex() {
        return this.lineIndex;
    }

    public int getStartOffset() {
        return startPosition.getOffset() - buffer.getLineStartOffset(getLineNumber());
    }

    public int getEndOffset() {
        return endPosition.getOffset() - buffer.getLineStartOffset(getLineNumber());
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    /**
	 * Returns the line number of the task, which takes into
	 * account the changes in the associtaed buffer
	 * @return The line number of the task as found in the associated buffer
	 */
    public int getLineNumber() {
        if (startPosition != null) {
            return buffer.getLineOfOffset(startPosition.getOffset());
        } else {
            return lineIndex;
        }
    }

    /**
	 * Provides String representation of the object.
	 * @return A String containing the line number and text of the
	 * formatted comment.
	 */
    public String toString() {
        return "[" + this.getLineNumber() + "]" + this.text;
    }

    private Buffer buffer;

    private Icon icon;

    private String text;

    private int startOffset;

    private int endOffset;

    private int lineIndex;

    private Position position;

    private Position startPosition;

    private Position endPosition;
}
