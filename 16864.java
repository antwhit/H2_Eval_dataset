import processing.core.*;
import processing.xml.*;
import arb.soundcipher.*;
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

public class UnlimitedArt {

    /**
	 * Notes and drawing are synchronised by the Draw framerate. Three
	 * SoundChiper instances are used to enable independent parts. Constrained
	 * randomness maintains a balance of order and variety. The music will
	 * continue as long as the drawing does.
	 * 
	 * SoundCipher library example by Andrew R. Brown
	 */
    SoundCipher sc = null;

    SoundCipher sc2 = null;

    SoundCipher sc3 = null;

    float[] pitchSet = { 57, 60, 60, 60, 62, 64, 67, 67, 69, 72, 72, 72, 74, 76, 79 };

    float setSize = pitchSet.length;

    float keyRoot = 50;

    float density = 0.8f;

    double xold = 0.0;

    double x, y;

    double yold = 0.0;

    double a = 1.4;

    double b = 0.3;

    int frameR = 8;

    int instrument = 1;

    public void setup(PApplet pApplet) {
        sc = new SoundCipher(pApplet);
        sc3 = new SoundCipher(pApplet);
        sc2 = new SoundCipher(pApplet);
        pApplet.frameRate(frameR);
        sc3.instrument(119);
        sc.instrument(33);
    }

    public void draw(PApplet pApplet) {
        sc2.instrument(instrument);
        if (pApplet.frameCount % 6 == 0) {
            if (pApplet.random(1) < density) {
                x = 1 + yold - a * xold * xold;
                y = b * xold;
                sc.playNote((int) (x * 36) + 48, pApplet.random(90) + 30, 32);
            }
        }
        if (pApplet.frameCount % 16 == 0) {
            float[] pitches = { pitchSet[(int) pApplet.random(setSize)] + keyRoot - 12, pitchSet[(int) pApplet.random(setSize)] + keyRoot - 12 };
            sc3.playChord(pitches, pApplet.random(50) + 30, 4.0f);
        }
        if (pApplet.frameCount % 64 == 0) {
            pApplet.frameRate(frameR);
        }
    }

    public void playPhrase(PApplet pApplet) {
        float rand = pApplet.random(pitchSet.length - 3);
        float[] prhase = new float[3];
        for (int i = 0; i < 3; i++) {
            prhase[i] = pitchSet[(int) (rand + i)];
        }
        float[] dynamics = new float[3];
        float[] durations = new float[3];
        for (int i = 0; i < 3; i++) {
            dynamics[i] = pApplet.random(40) + 70;
            durations[i] = pApplet.random(1) + 1;
        }
        sc2.playPhrase(prhase, dynamics, durations);
        instrument = (int) pApplet.random(127);
    }

    public void keyPressed(int key) {
        if (key == 'q') {
            instrument++;
        } else if (key == 'a') {
            instrument--;
        }
        if (instrument > 127) {
            instrument = 127;
        }
        if (instrument < 0) {
            instrument = 0;
        }
        System.out.println("current instrument " + instrument);
    }

    public static void main(String args[]) {
        PApplet.main(new String[] { "--bgcolor=#666666", "--stop-color=#cccccc", "UnlimitedArt" });
    }
}
