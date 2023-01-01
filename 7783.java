import java.awt.Color;
import deadpixel.keystone.CornerPinSurface;
import deadpixel.keystone.Keystone;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class DisplayImage implements Drawer {

    Keystone ks;

    CornerPinSurface surface;

    PGraphics offscreen;

    PImage img;

    public void draw(PApplet applet) {
        PVector surfaceMouse = surface.getTransformedMouse();
        offscreen.beginDraw();
        offscreen.background(255);
        offscreen.image(img, 0, 0);
        offscreen.fill(0, 255, 0);
        offscreen.endDraw();
        applet.background(0);
        surface.render(offscreen);
        int y = 240;
        int x = 320;
        int xOffset = 40;
        int yOffset = 40;
        int size = 10;
        applet.fill(Color.RED.getRGB());
        applet.ellipse(xOffset, yOffset, size, size);
        applet.ellipse(xOffset + x, yOffset, size, size);
        applet.ellipse(xOffset + x, yOffset + y, size, size);
        applet.ellipse(xOffset, yOffset + y, size, size);
        applet.fill(applet.get(applet.mouseX, applet.mouseY));
        applet.ellipse(applet.mouseX, applet.mouseY, 15, 15);
        Color color2 = new Color(applet.get(applet.mouseX, applet.mouseY));
        System.out.println("color " + color2.getRed() + "" + color2.getGreen() + "" + color2.getBlue());
    }

    public void keyPressed(int key) {
        switch((char) key) {
            case 'c':
                ks.toggleCalibration();
                break;
            case 'l':
                ks.load();
                break;
            case 's':
                ks.save();
                break;
        }
    }

    public void setup(PApplet applet) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        applet.size(400, 400, PApplet.P3D);
        ks = new Keystone(applet);
        surface = ks.createCornerPinSurface(320, 240, 20);
        offscreen = applet.createGraphics(400, 300, PApplet.P2D);
        img = applet.createImage(120, 120, PApplet.ARGB);
        for (int i = 0; i < img.pixels.length; i++) {
            img.pixels[i] = applet.color(0, 90, 102, i % img.width * 2);
        }
    }
}
