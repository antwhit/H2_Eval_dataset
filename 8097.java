import java.io.IOException;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

/**
 *
 * @author  snieradka
 */
public class Stations extends Thread {

    protected Main parent;

    private static int direction = 0;

    private String lastLine = "";

    public StationsList[] stationsList;

    public boolean running = true;

    public static final int SET_LINIA = 1;

    public static final int INIT = 0;

    public int whatToDo = INIT;

    class StationsList extends List {

        private short[] stationsList = null;

        public StationsList(Main parent) {
            super("Wybierz", List.IMPLICIT);
            setCommandListener(parent);
        }
    }

    /**
	 * constructor
	 */
    public Stations(Main _parent) {
        parent = _parent;
        stationsList = new StationsList[2];
        stationsList[0] = new StationsList(parent);
        stationsList[1] = new StationsList(parent);
    }

    public StationsList getDisplayedStationsList() {
        return stationsList[direction];
    }

    public static int getKierunek() {
        return direction;
    }

    public static void setDirection(int i) {
        direction = i;
    }

    public String getLastLinia() {
        return lastLine;
    }

    public void setLastLine(String s) {
        lastLine = s;
    }

    public synchronized void go() {
        notify();
    }

    private void setLine() {
        while (stationsList[direction].size() > 0) stationsList[direction].delete(0);
        try {
            stationsList[direction].setTitle(parent.db.getLineName(lastLine, direction));
            stationsList[direction].stationsList = parent.db.getStations(lastLine, direction);
        } catch (IOException ioe) {
            parent.Alert("B��d", "Nie mam takiej linii: " + lastLine, AlertType.ERROR);
        }
        for (int i = 0; i < stationsList[direction].stationsList.length; i++) {
            stationsList[direction].append((i + 1) + "." + parent.db.getStationName((stationsList[direction].stationsList[i])), null);
        }
    }

    public void changeDirection() throws IOException {
        if (lastLine != "") {
            int selected = stationsList[direction].getSelectedIndex();
            if (direction == 0) direction = 1; else direction = 0;
            setLastLine(lastLine);
            setDirection(direction);
            setLine();
            if (selected > 0) {
                selected = stationsList[direction].stationsList.length - selected - 1;
            }
            if (selected >= 0 && selected < stationsList[direction].stationsList.length) {
                stationsList[direction].setSelectedIndex(selected, true);
            }
        }
    }

    public short getSelectedStation() {
        return stationsList[direction].stationsList[stationsList[direction].getSelectedIndex()];
    }

    public short getStationsCount() {
        return (short) stationsList[direction].stationsList.length;
    }

    public void addCommand(Command c) {
        for (int i = 0; i < 2; ++i) {
            stationsList[i].addCommand(c);
        }
    }

    public int getSelectedIndex() {
        return stationsList[direction].getSelectedIndex();
    }

    /**
	 * Metoda spe�niaj�ca interface Runnalble na potrzeby klasy Thread.
	 *
	 */
    public synchronized void run() {
        while (running) {
            try {
                wait();
            } catch (InterruptedException ie) {
            }
            if (running) {
                if (whatToDo == SET_LINIA) {
                    setLine();
                    parent.setNextDisplayable(parent.kind);
                } else if (whatToDo == INIT) {
                    try {
                        DB.readLines();
                        DB.readStations();
                        DB.readDescriptions();
                        running = false;
                    } catch (Exception ex) {
                        parent.Alert("B��d", "B��d inicjacji: " + ex.getMessage(), AlertType.ERROR);
                    }
                    parent.setNextDisplayable(parent.mainMenu);
                }
            }
        }
    }
}
