import processing.core.*;
import processing.xml.*;
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;
import java.util.regex.*;

public class CLEyeMulticamTest extends PApplet {

    int numCams;

    CLCamera myCameras[] = new CLCamera[2];

    PImage myImages[] = new PImage[2];

    int cameraWidth = 640;

    int cameraHeight = 480;

    int cameraRate = 30;

    boolean animate = true;

    float zoomVal, zoomDelta;

    float rotateVal, rotateDelta;

    public void setup() {
        if (!setupCameras()) exit();
        if (animate) setupAnimation();
        smooth();
    }

    public void draw() {
        for (int i = 0; i < numCams; i++) {
            myCameras[i].getCameraFrame(myImages[i].pixels, (i == 0) ? 1000 : 0);
            myImages[i].updatePixels();
            image(myImages[i], cameraWidth * i, 0);
        }
        if (animate) updateAnimation();
    }

    public boolean setupCameras() {
        println("Getting number of cameras");
        numCams = CLCamera.cameraCount();
        println("Found " + numCams + " cameras");
        if (numCams == 0) return false;
        for (int i = 0; i < numCams; i++) {
            println("Camera " + (i + 1) + " UUID " + CLCamera.cameraUUID(i));
            myCameras[i] = new CLCamera(this);
            myCameras[i].createCamera(i, CLCamera.CLEYE_COLOR, CLCamera.CLEYE_VGA, cameraRate);
            myCameras[i].startCamera();
            myImages[i] = createImage(cameraWidth, cameraHeight, RGB);
        }
        size(cameraWidth * numCams, cameraHeight);
        println("Complete Initializing Cameras");
        return true;
    }

    public void setupAnimation() {
        zoomVal = 0;
        zoomDelta = TWO_PI / 75.0f;
        rotateVal = 0;
        rotateDelta = TWO_PI / 125.0f;
    }

    public void updateAnimation() {
        myCameras[0].setCameraParam(CLCamera.CLEYE_HKEYSTONE, (int) (150 * sin(rotateVal)));
        myCameras[0].setCameraParam(CLCamera.CLEYE_VKEYSTONE, (int) (200 * cos(rotateVal)));
        if (numCams > 1) {
            myCameras[1].setCameraParam(CLCamera.CLEYE_ZOOM, (int) (200 * sin(zoomVal)));
        }
        rotateVal += rotateDelta;
        zoomVal += zoomDelta;
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "CLEyeMulticamTest" });
    }
}
