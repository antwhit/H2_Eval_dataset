import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

/**	<titleabbrev>DisplaySoundbank</titleabbrev>
	<title>Displaying instruments in Soundbanks</title>

	<formalpara><title>Purpose</title>
	<para>All instruments in a soundbank are listed with their patch
	numbers and names. Optionally, a custom soundbank is
	loaded.</para> </formalpara>

	<formalpara><title>Usage</title>
	<para>
	<cmdsynopsis><command>java DisplaySoundbank</command>
	<arg choice="opt"><replaceable class="parameter">soundbank</replaceable></arg>
	</cmdsynopsis>
	</para></formalpara>

	<formalpara><title>Parameters</title>
	<variablelist>
	<varlistentry>
	<term><replaceable class="parameter">soundbank</replaceable></term>
	<listitem><para>the filename of a custom soundbank to be loaded. If not given, the default soundbank is used.</para></listitem>
	</varlistentry>
	</variablelist>
	</formalpara>

	<formalpara><title>Bugs, limitations</title>
	<para>Using a custom soundbank even if no default soundbank is
	available only works with JDK 1.5.0 and later.</para>
	</formalpara>

	<formalpara><title>Source code</title>
	<para>
	<ulink url="DisplaySoundbank.java.html">DisplaySoundbank.java</ulink>
	</para>
	</formalpara>

*/
public class DisplaySoundbank {

    private static final boolean DEBUG = true;

    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        Soundbank soundbank = null;
        if (args.length == 1) {
            File file = new File(args[0]);
            soundbank = MidiSystem.getSoundbank(file);
        } else if (args.length > 1) {
            printUsageAndExit();
        } else {
            Synthesizer synth = null;
            synth = MidiSystem.getSynthesizer();
            if (DEBUG) out("Synthesizer: " + synth);
            synth.open();
            soundbank = synth.getDefaultSoundbank();
            synth.close();
        }
        if (soundbank == null) {
            out("no soundbank");
            System.exit(1);
        }
        out("------------------------------------");
        out("Soundbank: " + soundbank);
        out("Name: " + soundbank.getName());
        out("Description: " + soundbank.getDescription());
        out("Vendor: " + soundbank.getVendor());
        out("Version: " + soundbank.getVersion());
        out("------------------------------------");
        out("Instruments (instr#:[bank#, patch#] name):");
        Instrument[] aInstruments = soundbank.getInstruments();
        for (int i = 0; i < aInstruments.length; i++) {
            out("" + i + ":[" + aInstruments[i].getPatch().getBank() + ", " + aInstruments[i].getPatch().getProgram() + "] " + aInstruments[i].getName());
        }
    }

    private static void printUsageAndExit() {
        out("DisplaySoundbank: usage:");
        out("java DisplaySoundbank [<soundbankfilename>]");
        System.exit(1);
    }

    private static void out(String strMessage) {
        System.out.println(strMessage);
    }
}
