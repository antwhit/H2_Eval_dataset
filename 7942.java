import com.arcucomp.util.*;
import com.arcucomp.xmlplayground.*;
import org.w3c.dom.*;
import java.util.Stack;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.*;
import org.apache.xerces.parsers.*;
import org.w3c.dom.Document;
import java.io.*;
import java.io.*;
import org.xml.sax.*;
import java.util.*;

public class XP_Add_To_List_File {

    public static void main(String[] args) {
        XCLogger xclogger = new XCLogger(6);
        int position = 0;
        int positionF = 5;
        int positionAttF = 6;
        try {
            XMLPlayground_i xp1 = new XMLPlayground_i();
            xp1.setDebugLevel(8);
            xclogger.println(1, "playergames.xml test");
            String tmpxecommandAppend = "playergames.xml";
            String games = "games.game[3](name)";
            String games2 = "games.game";
            String attribute = "name";
            String conduit = games;
            String testString = "It Works";
            String testStringAtt = "It Works Att";
            position = 0;
            xp1.addXML(tmpxecommandAppend, "true");
            conduit = games2;
            int count = xp1.countXMLList(conduit, attribute, false, position);
            xclogger.println(1, "XMLPlayground::countXMLList found " + count + " entries  for conduit=[" + conduit + "] attribute =[" + attribute + "]");
            xclogger.println(1, "XMLPlayground before XMLPlayground::getXMLList conduit=[" + conduit + "] attribute =[" + attribute + "]");
            Vector vec = xp1.getXMLList(conduit, attribute, false, position);
            xclogger.println(1, vec);
            count = xp1.countXMLList(conduit, attribute, false, position);
            xp1.setXML(conduit + "[" + (count) + "](" + attribute + ")", testStringAtt, 1, position);
            vec = xp1.getXMLList(conduit, attribute, false, position);
            xclogger.println(1, vec);
            xclogger.println(1, "XMLPlayground after  XMLPlayground::getXMLList");
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
    }
}
