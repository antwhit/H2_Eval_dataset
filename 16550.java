import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.event.*;
import java.awt.event.MouseEvent;

class BeatCanvas extends Canvas implements MouseInputListener {

    private Color color;

    private boolean first;

    private int flash;

    public BeatCanvas(Color c) {
        super();
        first = true;
        flash = 0;
        color = c;
        setBackground(color);
        addMouseListener(this);
        BeatAgent[] baS = AgentBeats.beatAgentHandle.getAgentList();
        setSize(360, (baS.length * 15) + 20);
    }

    public void paint(Graphics g) {
        if (!first) {
            if (flash > 0) {
                setBackground(Color.white);
                flash--;
            } else {
                setBackground(color);
            }
            BeatAgent[] baS = AgentBeats.beatAgentHandle.getAgentList();
            setSize(345, (baS.length * 15) + 10);
            for (int i = 0; i < baS.length; i++) {
                baS[i].drawBeat(g);
            }
        }
        if (first) {
            first = false;
        }
    }

    public void flash(int time) {
        flash = time;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        BeatAgent[] baS = AgentBeats.beatAgentHandle.getAgentList();
        for (int i = 0; i < baS.length; i++) {
            baS[i].processBeatCanvasMouseclick(e);
        }
    }
}
