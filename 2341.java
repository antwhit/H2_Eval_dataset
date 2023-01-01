import nanoxml.*;
import java.util.*;
import java.io.*;
import jacol.Jacol;

public class xml {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: xml <filename>");
            System.exit(1);
        }
        new xml(args[0]);
    }

    public xml(String filename) {
        try {
            XMLElement x = new XMLElement();
            x.parseFromReader(new FileReader(filename));
            showChildren(x);
            Jacol.shutdown();
        } catch (Exception _e) {
            _e.printStackTrace();
        }
    }

    public void showChildren(XMLElement x) throws Exception {
        Vector kids = x.getChildren();
        for (int i = 0; i < kids.size(); i++) {
            XMLElement child = (XMLElement) kids.elementAt(i);
            System.out.println("Element name='" + child.getTagName() + "', contents='" + child.getContents() + "'");
            showChildren(child);
        }
    }
}
