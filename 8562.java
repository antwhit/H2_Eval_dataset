import java.io.IOException;
import java.util.Timer;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Gauge entertaining the user while waiting.
 * Widely inspired from Jonathan Knudsen's
 * AnimationGauge.java http://kickbutt.jonathanknudsen.com/
 * */
public class WaitGauge extends CustomItem {

    /** Visual of the gauge. */
    private final Image image_;

    private int index_;

    /** Number of frames */
    private final int n_;

    /** Frame width and height */
    private final int s_;

    private Timer timer_;

    private ZZappett3TimerTask timerTask_;

    public WaitGauge() throws IOException {
        super("");
        image_ = Image.createImage("/gauge.png");
        s_ = image_.getHeight();
        n_ = image_.getWidth() / s_ * 4;
    }

    public void showNotify() {
        timer_ = new Timer();
        timerTask_ = new ZZappett3TimerTask(this);
        timer_.schedule(timerTask_, 0, 150);
    }

    public void hideNotify() {
        if (timer_ != null) {
            timer_.cancel();
            timerTask_ = null;
            timer_ = null;
        }
    }

    /** Used by ZZTimeTask */
    public void timeStep() {
        index_ = (index_ + 1) % n_;
        repaint();
    }

    public void paint(Graphics g, int w, int h) {
        int i = index_ % (n_ / 4);
        int x = i * s_;
        int ti = (index_ - i) / (n_ / 4);
        int tx = Sprite.TRANS_NONE;
        if (ti == 1) tx = Sprite.TRANS_ROT90;
        if (ti == 2) tx = Sprite.TRANS_ROT180;
        if (ti == 3) tx = Sprite.TRANS_ROT270;
        g.drawRegion(image_, x, 0, s_, s_, tx, w / 2, h / 2, Graphics.HCENTER | Graphics.VCENTER);
    }

    public int getMinContentWidth() {
        return s_;
    }

    public int getMinContentHeight() {
        return s_;
    }

    public int getPrefContentWidth(int h) {
        return s_;
    }

    public int getPrefContentHeight(int width) {
        return s_;
    }
}
