import java.util.*;
import java.awt.*;

public class Group {

    Vector particles = new Vector();

    @SuppressWarnings("unchecked")
    Group(Particles p, Particles p2) {
        particles.add(p);
        particles.add(p2);
    }

    boolean qualify(Particles p2) {
        for (int x = 0; x < particles.size(); x++) {
            Particles p = (Particles) particles.get(x);
            if (qualify(p, p2)) return true;
        }
        return false;
    }

    boolean qualify(Group grp2) {
        for (int x = 0; x < grp2.particles.size(); x++) {
            Particles p2 = (Particles) grp2.particles.get(x);
            if (qualify(p2)) return true;
        }
        return false;
    }

    static boolean qualify(Particles p, Particles p2) {
        if (Visualize.rangeThreshold > Math.abs(p.position.width - p2.position.width) && Visualize.rangeThreshold > Math.abs(p.position.height - p2.position.height)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    void add(Particles p) {
        particles.add(p);
    }

    @SuppressWarnings("unchecked")
    Group join(Group grp2) {
        for (int x = 0; x < grp2.particles.size(); x++) {
            Particles p = (Particles) particles.get(x);
            Particles p2 = (Particles) grp2.particles.get(x);
            if (p == p2) {
                for (int y = 0; y < grp2.particles.size(); y++) {
                    if (y == x) continue;
                    particles.add(grp2.particles.get(y));
                }
            }
        }
        return this;
    }

    void drawLines(Graphics gbuffer) {
        for (int x = 0; x < (particles.size() - 1); x++) {
            Particles p = (Particles) particles.get(x);
            Particles p2 = (Particles) particles.get(x + 1);
            gbuffer.drawLine(p.position.width, p.position.height, p2.position.width, p2.position.height);
        }
    }

    void remove() {
        for (int b = 0; b < (particles.size() - 1); b++) {
            Particles p = (Particles) particles.get(b);
            Particles p2 = (Particles) particles.get(b + 1);
            if (!qualify(p, p2)) {
                particles.removeElementAt(b + 1);
                b--;
            }
        }
    }
}
