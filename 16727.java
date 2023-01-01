import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import magenta.*;

/**
 * class Nqueens -
 *  An extension of a magenta agent that acts as a bulletin
 *  board for queens to find each other.  Also receives 
 *  events when Queens move and uses the information to
 *  draw a picture of the board.
 *
 *  <dl>
 *  <b>GdmoObject attributes:</b>
 *    <dd>QueensList - list of known queen hostname:ports</dd>
 *  </dl>
 * 
 * @author aloke mukherjee
 * @version
 * 2001.05.04 alokem creation
 */
public class Nqueens extends magenta.Agent {

    /** list of hostname:ports for registered queens */
    private String queenList;

    /** 
    * hashtable used to keep track of each queen's position 
    * format is: ("hostname:port", "row,column")
    */
    private Hashtable queenPositions;

    /** number of rows/columns on board - FIXME there should be only one for both */
    final int MAX_SIZE = 4;

    /** graphical display of board */
    private NqueensViewer theViewer;

    /**
   * Nqueens constructor -
   *  instantiate magenta components, init private members.
   *
   * @version
   * 2001.05.04 alokem creation
   */
    public Nqueens(String path, String name) {
        super(path, name);
        queenPositions = new Hashtable();
        queenList = new String();
        JFrame f = new JFrame("nqueens viewer");
        f.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        theViewer = new NqueensViewer(this);
        f.getContentPane().add("Center", theViewer);
        theViewer.init();
        f.pack();
        f.setSize(new Dimension(400, 400));
        f.show();
    }

    public Iterator queenIterator() {
        return queenPositions.values().iterator();
    }

    /**
   * Nqueens::setQueenList -
   *  adds a queen (identified by it's hostname:port) to
   *  the queenlist.
   *
   * @param queen   hostname:port to add to queenlist
   *
   * @return
   * "success"
   */
    public String setQueenList(String queen) {
        if (queenList.indexOf(queen) == -1) {
            queenList = queenList + queen + ",";
        }
        return new String("success");
    }

    /**
   * Nqueens::getQueenList -
   *  list of queens that have registered with this nqueens server.
   *
   * @return
   * comma-delimited list of hostname:ports of registered queens.
   *
   * @version
   * 2001.05.04 alokem creation
   */
    public String getQueenList() {
        return queenList;
    }

    /**
   * Nqueens::toString -
   *  display this object's fields.
   *
   * @return
   * string containing attribute value pairs.
   *
   * @version
   * 2001.05.04 alokem creation
   */
    public String toString() {
        return new String(super.toString() + " QueenList: " + queenList);
    }

    /**
   * Nqueens::handleEvent -
   *  update display of queen locations.
   *
   * @param eventreport   contents of event report.
   *
   * @return 
   * always returns "success"
   * 
   * @version
   * 2001.05.04 alokem creation
   */
    public String handleEvent(String eventreport) {
        EventReport event = new EventReport(eventreport);
        log("event@" + getHostInfo() + ": " + event);
        if ((event.getHostInfo() != null) && (event.getClassName().compareTo("Queen") == 0) && (event.getAttribute().compareTo("Position") == 0)) {
            queenPositions.put(event.getHostInfo(), event.getValue());
        }
        synchronized (theViewer) {
            theViewer.repaint();
        }
        log("queenPositions: " + queenPositions);
        return new String("success");
    }
}
