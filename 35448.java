import java.io.File;
import java.io.IOException;
import java.util.Date;
import jxl.*;
import jxl.read.biff.BiffException;
import java.util.ArrayList;

public class NameCheck {

    public static String upperCase(String n) {
        String[] name = n.split(" ");
        String[] nameUpper = new String[name.length];
        for (int j = 0; j < name.length; j++) {
            char nameHead = name[j].charAt(0);
            String nameHeadS = Character.toString(nameHead);
            nameHeadS = nameHeadS.toUpperCase();
            char nameHeadU = nameHeadS.charAt(0);
            char[] nameList = new char[n.length()];
            nameList[0] = nameHeadU;
            for (int i = 1; i < name[j].length(); i++) {
                nameList[i] = name[j].charAt(i);
            }
            nameUpper[j] = new String(nameList);
            nameUpper[j] = nameUpper[j].trim();
        }
        String nameFinal = nameUpper[0];
        for (int k = 1; k < nameUpper.length; k++) {
            nameFinal = nameFinal + " " + nameUpper[k];
        }
        return nameFinal;
    }

    public static String[] returnRow(String n) throws BiffException, IOException {
        String name = upperCase(n);
        Workbook workbook = Workbook.getWorkbook(new File("nameCheckDatabase2.xls"));
        Sheet sheet = workbook.getSheet(0);
        Cell[][] namesCells = new Cell[12][39];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 39; j++) {
                namesCells[i][j] = sheet.getCell(i, j);
            }
        }
        String[][] names = new String[12][39];
        for (int k = 0; k < 12; k++) {
            for (int l = 0; l < 39; l++) {
                if (namesCells[k][l].getContents() == "") {
                    names[k][l] = "none";
                } else {
                    names[k][l] = namesCells[k][l].getContents();
                }
            }
        }
        for (int K = 0; K < 12; K++) {
            for (int L = 0; L < 39; L++) {
                if (name.equals(names[K][L])) {
                    String[] row = new String[12];
                    for (int m = 0; m < 12; m++) {
                        row[m] = names[m][L];
                    }
                    return row;
                }
            }
        }
        String[] empty = new String[1];
        empty[0] = "Does not match";
        return empty;
    }

    public static ArrayList<String> rowToList(String[] row) {
        ArrayList<String> rowList = new ArrayList<String>();
        int N = row.length;
        for (int i = 0; i < N; i++) {
            if (!row[i].equals("none")) {
                rowList.add(row[i]);
            }
        }
        return rowList;
    }
}
