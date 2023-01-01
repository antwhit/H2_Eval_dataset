import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextKeyAdapter extends KeyAdapter {

    private WordWorker wordWorker;

    private JTextArea textArea;

    private static final long serialVersionUID = 7L;

    public TextKeyAdapter(WordWorker l, JTextArea source) {
        wordWorker = l;
        textArea = source;
    }

    public void keyTyped(KeyEvent evt) {
    }

    public void keyPressed(KeyEvent evt) {
        if (textArea.isEditable() == false) return;
        switch(evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                wordWorker.update();
                break;
        }
    }
}
