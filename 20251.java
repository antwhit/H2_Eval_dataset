import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UPBAControls extends Panel implements ActionListener, ItemListener {

    UPBA upba;

    Button exitb;

    Button playb;

    Button stopb;

    Button stepb;

    Checkbox loopb;

    Checkbox revb;

    Button resetb;

    Button skipbw;

    Label framecount;

    Button skipfw;

    Button endsetb;

    TextField lagview;

    Font boldFont = new Font("Monospaced", Font.BOLD, 14);

    public UPBAControls(UPBA u) {
        upba = u;
        playb = new Button("play");
        stopb = new Button("stop");
        stepb = new Button("step");
        loopb = new Checkbox("loop");
        revb = new Checkbox("revs");
        resetb = new Button("<<");
        skipbw = new Button("<");
        framecount = new Label("--/--");
        skipfw = new Button(">");
        endsetb = new Button(">>");
        lagview = new TextField("100");
        add(playb);
        playb.addActionListener(this);
        add(stopb);
        stopb.addActionListener(this);
        add(stepb);
        stepb.addActionListener(this);
        add(loopb);
        loopb.addItemListener(this);
        add(revb);
        revb.addItemListener(this);
        add(resetb);
        resetb.addActionListener(this);
        add(skipbw);
        skipbw.addActionListener(this);
        add(framecount);
        add(skipfw);
        skipfw.addActionListener(this);
        add(endsetb);
        endsetb.addActionListener(this);
        add(lagview);
        lagview.addActionListener(this);
        framecount.setFont(boldFont);
    }

    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();
        boolean rev_rem;
        boolean weregood = true;
        int lag = 100;
        if (src == playb && !upba.running) {
            upba.cont = true;
            upba.go();
            upba.running = true;
        } else if (src == stopb) {
            upba.cont = false;
        } else if (src == stepb && !upba.running) {
            upba.cont = false;
            upba.go();
            upba.running = true;
        } else if (src == resetb) {
            upba.set(0);
        } else if (src == skipbw) {
            upba.set(upba.currentFrameIndex - 1);
        } else if (src == skipfw) {
            upba.set(upba.currentFrameIndex + 1);
        } else if (src == endsetb) {
            upba.set(upba.game.npframes - 1);
        } else if (src == lagview) {
            try {
                lag = Integer.valueOf(lagview.getText()).intValue();
            } catch (NumberFormatException e) {
                weregood = false;
            }
            if (weregood) {
                lag = Math.min(lag, 999);
                upba.game.pframes[upba.currentFrameIndex].setLag(lag);
                lagview.setText(Integer.toString(lag));
            } else {
                lagview.setText("100");
            }
        }
        upba.game.resethist();
    }

    public void itemStateChanged(ItemEvent e) {
        Object src = e.getSource();
        boolean on = e.getStateChange() == ItemEvent.SELECTED;
        if (src == loopb) upba.loop = on; else if (src == revb) upba.rev = on;
    }

    public void updateCount() {
        if (upba.currentFrameIndex + 1 < 10 && upba.game.npframes < 10) {
            framecount.setText(" " + Integer.toString(upba.currentFrameIndex + 1) + "/" + Integer.toString(upba.game.npframes) + " ");
        } else if (upba.currentFrameIndex + 1 < 10 && upba.game.npframes >= 10) {
            framecount.setText(" " + Integer.toString(upba.currentFrameIndex + 1) + "/" + Integer.toString(upba.game.npframes));
        } else if (upba.currentFrameIndex + 1 >= 10 && upba.game.npframes >= 10) {
            framecount.setText(Integer.toString(upba.currentFrameIndex + 1) + "/" + Integer.toString(upba.game.npframes));
        } else if (upba.currentFrameIndex + 1 >= 10 && upba.game.npframes < 10) {
            framecount.setText(Integer.toString(upba.currentFrameIndex + 1) + "/" + Integer.toString(upba.game.npframes) + " ");
        }
    }

    public void updateLag() {
        PFrame aPFrame = upba.game.pframes[upba.currentFrameIndex];
        lagview.setText(Integer.toString(aPFrame.getLag()));
    }

    public void updateControls() {
        this.updateLag();
        this.updateCount();
    }
}
