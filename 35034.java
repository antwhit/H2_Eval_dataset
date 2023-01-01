import java.io.FileInputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;

public class XMLItemTools {

    private String iItemName = "";

    private String[] iItemFields = new String[] {};

    public String getItemName() {
        return iItemName;
    }

    public String[] getItemFields() {
        return iItemFields;
    }

    public void loadScheme(String rScheme) throws Exception {
        Properties scheme = new Properties();
        scheme.load(new FileInputStream(rScheme));
        Enumeration items = scheme.propertyNames();
        String itemName = null;
        String fieldList = null;
        if (items.hasMoreElements()) {
            itemName = (String) items.nextElement();
            fieldList = scheme.getProperty(itemName);
            Vector fields = new Vector();
            StringTokenizer st = new StringTokenizer(fieldList, ",");
            while (st.hasMoreTokens()) {
                String field = st.nextToken();
                fields.addElement(field);
            }
            String[] fieldsArray = new String[fields.size()];
            fields.copyInto(fieldsArray);
            iItemName = itemName;
            iItemFields = fieldsArray;
        } else {
            throw new Exception("Can't find scheme: " + rScheme);
        }
    }

    public static void main(String rArgs[]) {
        try {
            XMLItemTools xit = new XMLItemTools();
            xit.loadScheme("bookscheme.txt");
            System.out.println("Item: " + xit.getItemName());
            System.out.print("Fields: ");
            String[] fields = xit.getItemFields();
            int numFields = fields.length;
            for (int fieldI = 0; fieldI < numFields; fieldI++) {
                System.out.print(fields[fieldI] + ", ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
