import java.util.ArrayList;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.geom.Point2D;
import javax.media.jai.PerspectiveTransform;
import javax.swing.*;
import wiiremotej.*;
import wiiremotej.event.*;
import javax.sound.sampled.*;
import java.io.*;

/**
 * Implements WiiRemoteListener and acts as a general test class. Note that you can ignore the main method pretty much, as it mostly has to do with the graphs and GUIs.
 * At the very end though, there's an example of how to connect to a remote and how to prebuffer audio files.
 * 
 */
public class WiiIRMouse extends WiiRemoteAdapter {

    private static final long transitValidateTime = 25;

    private Point2D lastIRPos, lastScreenPos;

    private WiiRemote remote;

    private PerspectiveTransform pTrans;

    private boolean pressedState = false;

    private long transitPressedTime = 0;

    private WiiInterpolatedMouse imouse;

    private TrayMenu tray;

    private int ID;

    private double battStat;

    public WiiIRMouse(WiiRemote remote, int ID) throws IllegalArgumentException, IllegalStateException, IOException {
        byte[] sense1 = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, (byte) (0x90 & 0xFF), 0x00, 0x41 };
        byte[] sense2 = new byte[] { 0x40, 0x00 };
        this.ID = ID;
        this.tray = tray;
        this.remote = remote;
        this.remote.setIRSensorEnabled(true, WRIREvent.BASIC, sense1, sense2);
        try {
            remote.setIRSensorEnabled(true, WRIREvent.BASIC);
            remote.setLEDIlluminated(0, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lastIRPos = new Point2D.Double(0, 0);
        lastScreenPos = new Point2D.Double(0, 0);
    }

    public void setTransform(PerspectiveTransform pTrans) {
        this.pTrans = pTrans;
    }

    public PerspectiveTransform getTransform() {
        return pTrans;
    }

    public void disconnected() {
        System.out.println("Remote disconnected... Please Wii again.");
        System.exit(0);
    }

    public void statusReported(WRStatusEvent evt) {
        System.out.println("Battery level: " + (double) evt.getBatteryLevel() / 2 + "%");
        System.out.println("Continuous: " + evt.isContinuousEnabled());
        battStat = evt.getBatteryLevel();
    }

    public double getBattStat() {
        return battStat;
    }

    public void IRInputReceived(WRIREvent evt) {
        IRLight light = evt.getIRLights()[0];
        if (light != null) {
            if (!pressedState) {
                if (transitPressedTime == 0) transitPressedTime = System.currentTimeMillis(); else {
                    if (transitPressedTime + transitValidateTime < System.currentTimeMillis()) {
                        pressedState = true;
                        transitPressedTime = 0;
                        System.out.println("toggle to pressed " + ID);
                    }
                }
            }
            if (pTrans != null) lastScreenPos = pTrans.transform(new Point2D.Double(light.getX(), 1.0 - light.getY()), null);
            lastIRPos.setLocation(light.getX(), light.getY());
        } else {
            if (pressedState) {
                if (transitPressedTime == 0) transitPressedTime = System.currentTimeMillis(); else {
                    if (transitPressedTime + transitValidateTime < System.currentTimeMillis()) {
                        transitPressedTime = 0;
                        pressedState = false;
                        System.out.println("toggle to not pressed " + ID);
                    }
                }
            }
        }
        if (imouse != null) imouse.UpdateEvent();
    }

    public void registerInterpolatedMouse(WiiInterpolatedMouse mouse) {
        imouse = mouse;
    }

    public boolean isPressed() {
        return pressedState;
    }

    public Point2D lastScreenPos() {
        return lastScreenPos;
    }

    public Point2D lastLightPos() {
        return lastIRPos;
    }

    public WiiRemote getRemote() {
        return remote;
    }
}
