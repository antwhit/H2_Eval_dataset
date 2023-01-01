import java.util.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class CheckPhasers extends Thread {

    GameWindow gw;

    ArrayList<Ship> ships;

    ArrayList<Phaser> phasers;

    public CheckPhasers(GameWindow window) {
        gw = window;
        phasers = new ArrayList<Phaser>();
    }

    public ArrayList<Phaser> getPhasers() {
        return phasers;
    }

    public void addPhaser(Phaser p) {
        phasers.add(p);
    }

    public Point getRotatedPoint(double originX, double originY, double x, double y, double angle) {
        double cost, sint, xr, yr, newX, newY;
        cost = Math.cos(angle);
        sint = Math.sin(angle);
        xr = x - originX;
        yr = y - originY;
        newX = cost * xr - sint * yr;
        newY = sint * xr + cost * yr;
        newX += originX;
        newY += originY;
        return new Point((int) newX, (int) newY);
    }

    public void run() {
        while (true) {
            ships = new ArrayList<Ship>(gw.shipManager.getShips());
            if (gw.mouseDown && ships.get(0).battery > 0 && gw.playerShip.hp > 0) {
                ships.get(0).battery -= 1;
                phasers.add(new Phaser(gw.playerShip.coords().x + 18, gw.playerShip.coords().y + 18, gw.mouseX, gw.mouseY, 18, 50, 15));
            }
            for (Ship s : ships) {
                if (s != gw.playerShip && s.battery > 10 && gw.playerShip.hp > 0) {
                    if (Math.random() < gw.fireRate) {
                        s.battery--;
                        phasers.add(new Phaser((int) s.x + 18, (int) s.y + 18, (int) ((Math.random() - 0.5) * 10) + (int) gw.playerShip.x, (int) ((Math.random() - 0.5) * 10) + (int) gw.playerShip.y, 18, 20, 15));
                    }
                }
            }
            Point topLeft, topRight, bottomRight, bottomLeft;
            outer: for (Phaser p : phasers) {
                p.update();
                for (Ship s : ships) {
                    if (p.line.intersects(s.shipRect)) {
                        double w = s.shipRect.getWidth();
                        double h = s.shipRect.getHeight();
                        int offset = (int) Math.ceil(w * Math.random());
                        gw.explosions.add(new Explosion((int) s.x + offset, (int) s.y + offset, 25));
                        topLeft = getRotatedPoint(s.x + w / 2, s.y + h / 2, s.x, s.y, -s.angle);
                        topRight = getRotatedPoint(s.x + w / 2, s.y + h / 2, s.x + w, s.y, -s.angle);
                        bottomLeft = getRotatedPoint(s.x + w / 2, s.y + h / 2, s.x, s.y + h, -s.angle);
                        bottomRight = getRotatedPoint(s.x + w / 2, s.y + h / 2, s.x + w, s.y + h, -s.angle);
                        if (p.line.intersectsLine(topLeft.getX(), topLeft.getY(), bottomLeft.getX(), bottomLeft.getY())) s.damage(s.PHASERDAMAGE, 0);
                        if (p.line.intersectsLine(topRight.getX(), topRight.getY(), bottomRight.getX(), bottomRight.getY())) s.damage(s.PHASERDAMAGE, 1);
                        if (p.line.intersectsLine(topLeft.getX(), topLeft.getY(), topRight.getX(), topRight.getY())) s.damage(s.PHASERDAMAGE, 2);
                        if (p.line.intersectsLine(bottomLeft.getX(), bottomLeft.getY(), bottomRight.getX(), bottomRight.getY())) s.damage(s.PHASERDAMAGE, 3);
                        phasers.remove(p);
                        break outer;
                    }
                }
                if (!p.line.intersects(0, 0, 640, 480)) {
                    phasers.remove(p);
                    break outer;
                }
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
}
