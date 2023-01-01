import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Applet for playing sounds via javascript.  The applet is configured
 * with a list of sounds to play, each of which may be played from
 * javascript using play("sound");  If no "soundn" parameter is supplied, 
 * then "sound.au" is assumed.
 *
 * <PARAM NAME="sounds" VALUE="sound1 sound2 ... soundn">
 * <PARAM NAME="sound1" VALUE="sound1.au">
 * ...
 * <PARAM NAME="soundn" VALUE="soundn.au">
 * <PARAM NAME="play" VALUE="sound">
 * <PARAM NAME="debug" VALUE="on">
 *
 */
public class notify extends Applet {

    String prev = null;

    Hashtable sounds = new Hashtable();

    boolean debug;

    public void init() {
        debug = (getParameter("debug") != null);
        String s = getParameter("sounds");
        log("Init 2.2 " + s);
        if (s == null) {
            return;
        }
        if (s.equals(prev)) {
            log("soundlist the same, don't update");
            return;
        }
        sounds.clear();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            String name = st.nextToken();
            String clip = getParameter(name);
            if (clip == null) {
                clip = name + ".au";
            }
            log("  fetching clip (" + clip + ") for sound " + name);
            sounds.put(name, getAudioClip(getCodeBase(), clip));
            String play = getParameter("play");
            if (play != null) {
                play(play);
            }
        }
    }

    public boolean play(String s) {
        log("play called");
        log("playing " + s);
        AudioClip sound = (AudioClip) sounds.get(s);
        if (sound != null) {
            sound.play();
            showStatus(s + " beep");
            return true;
        } else {
            showStatus("no beep!");
            log("  sound failed");
            return false;
        }
    }

    public boolean playText(String url) {
        log("fetching: " + url);
        AudioClip sound = getAudioClip(getCodeBase(), url);
        if (sound != null) {
            sound.play();
            showStatus(url);
            return true;
        } else {
            return false;
        }
    }

    void log(String message) {
        if (debug) {
            System.err.println(message);
        }
    }
}
