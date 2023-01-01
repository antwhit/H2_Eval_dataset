import java.awt.*;
import java.awt.image.*;

public class Ship {

    int[] shields = new int[4];

    int hp;

    double x, y;

    int initialX, initialY;

    double speed, angle;

    int torpedoes;

    int battery;

    double maxSpeed = 1.5;

    double acceleration = 1;

    Color color;

    Rectangle shipRect;

    BufferedImage shipBuffer;

    int recoil = 0;

    int maxBattery = 20;

    int shieldStrength = 100;

    int PHASERDAMAGE = 5;

    final double SHIPTURN = 0.035;

    public Ship(int xPos, int yPos, Color c) {
        for (int i = 0; i < 4; i++) shields[i] = 100;
        hp = 100;
        torpedoes = 0;
        battery = maxBattery;
        speed = 0;
        angle = (Math.PI / 2);
        x = xPos;
        y = yPos;
        initialX = (int) x;
        initialY = (int) y;
        color = c;
        shipRect = new Rectangle((int) x, (int) y, 35, 35);
        shipBuffer = new BufferedImage(35, 35, BufferedImage.TYPE_INT_RGB);
    }

    public Point coords() {
        return new Point((int) x, (int) y);
    }

    public void advanceTurn() {
        speed = Math.min(speed, maxSpeed);
        speed = Math.max(speed, -1);
        x = Math.max(10, x);
        x = Math.min(x, 630);
        y = Math.max(10, y);
        y = Math.min(y, 470);
        if (angle > (Math.PI * 2)) angle -= (Math.PI * 2); else if (angle < 0) angle += (Math.PI * 2);
        double xVector = Math.sin(angle) * speed;
        double yVector = Math.cos(angle) * speed;
        x += xVector;
        y += yVector;
        shipRect.setLocation(new Point((int) x, (int) y));
    }

    public void damage(int mag, int shieldFace) {
        shields[shieldFace] -= mag;
        if (shields[shieldFace] < 0) {
            hp += shields[shieldFace];
            shields[shieldFace] = 0;
        }
    }

    public void automate() {
        final int EDGE_TURN = 70;
        if (speed < maxSpeed) speed += 0.1;
        if (x < 150) {
            if (y < 150) {
                angle += Math.random() * SHIPTURN * ((150 - x) / EDGE_TURN);
            } else {
                angle -= Math.random() * SHIPTURN * ((150 - x) / EDGE_TURN);
            }
        } else if (x > 540) {
            if (y < 150) {
                angle += Math.random() * SHIPTURN * ((x - 540) / EDGE_TURN);
            } else {
                angle -= Math.random() * SHIPTURN * ((x - 540) / EDGE_TURN);
            }
        }
        if (y < 150) {
            if (x < 150) {
                angle += Math.random() * SHIPTURN * ((150 - y) / EDGE_TURN);
            } else {
                angle += Math.random() * SHIPTURN * ((150 - y) / EDGE_TURN);
            }
        } else if (y > 350) {
            if (x < 150) {
                angle -= Math.random() * SHIPTURN * ((y - 350) / EDGE_TURN);
            } else {
                angle -= Math.random() * SHIPTURN * ((y - 350) / EDGE_TURN);
            }
        }
    }

    public void automate(int playerX, int playerY) {
    }
}
