import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class exportToXMLUtilities extends JFrame {

    private Component c;

    private JFileChooser fc;

    private LinkedList surrogateList;

    private int autonumber;

    public exportToXMLUtilities(Component componentToSave, LinkedList surrogateList, int autonumber) {
        this.c = componentToSave;
        this.surrogateList = surrogateList;
        this.autonumber = autonumber;
        saveUtilities();
    }

    public void saveUtilities() {
        fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file.getPath());
                PrintWriter p = new PrintWriter(out);
                p.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
                p.println("<ontologyChart title=\"templete ontology chart\" domain=\"misc\">");
                for (int i = 0; i < surrogateList.size(); i++) {
                    p.println("<surrogate>");
                    p.println("<id>" + ((surrogate) surrogateList.get(i)).id + "</id>");
                    p.println("<label>" + ((surrogate) surrogateList.get(i)).label + "</label>");
                    p.println("<sort>" + ((surrogate) surrogateList.get(i)).sort + "</sort>");
                    p.println("<type>" + ((surrogate) surrogateList.get(i)).type + "</type>");
                    p.println("<firstAntecedent>" + ((surrogate) surrogateList.get(i)).antecedent1 + "</firstAntecedent>");
                    p.println("<secondAntecedent>" + ((surrogate) surrogateList.get(i)).antecedent2 + "</secondAntecedent>");
                    p.println("<startAuthority>" + ((surrogate) surrogateList.get(i)).authority1 + "</startAuthority>");
                    p.println("<finishAuthority>" + ((surrogate) surrogateList.get(i)).authority2 + "</finishAuthority>");
                    p.println("<startActionTime>" + ((surrogate) surrogateList.get(i)).actiontime1 + "</startActionTime>");
                    p.println("<finishActionTime>" + ((surrogate) surrogateList.get(i)).actiontime2 + "</finishActionTime>");
                    p.println("<recordStartTime>" + ((surrogate) surrogateList.get(i)).recordtime1 + "</recordStartTime>");
                    p.println("<recordFinishTime>" + ((surrogate) surrogateList.get(i)).recordtime2 + "</recordFinishTime>");
                    p.println("</surrogate>");
                }
                p.println("</ontologyChart>");
                p.close();
            } catch (Exception exception) {
                System.err.println("Error writing to file");
            }
        }
    }
}
