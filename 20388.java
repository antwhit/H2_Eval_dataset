import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parserCSVWG_code {

    public static void main(String args[]) {
        String archivo = "C:\\Documents and Settings\\administrador\\Escritorio\\SAQ-ASSE.csv";
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter("C:\\Documents and Settings\\administrador\\Escritorio\\saq_asse.sql");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        try {
            String linea = entrada.readLine();
            while (linea != null) {
                linea = linea.replaceAll("\\+", "").replaceAll("\\*", "").replaceAll("\\s+", " ");
                Pattern patron = Pattern.compile("(\\w{3}\\d{4}\\s*);([^;]+);;(.*);(.*);(.*);(.*);(.*)");
                Matcher matcher = patron.matcher(linea);
                if (matcher.find()) {
                    pw.println("INSERT INTO `SAQ_ASSEBean` (`cod`,`descrip`,`cie9_eq`,`asa_1`,`asa_2`,`asa_3`,`asa_4`,`asa_5`) VALUES   ('" + matcher.group(1).toUpperCase().trim() + "','" + matcher.group(2).toUpperCase() + "','','" + matcher.group(3).toUpperCase().trim() + "','" + matcher.group(4).toUpperCase().trim() + "','" + matcher.group(5).toUpperCase().trim() + "','" + matcher.group(6).toUpperCase().trim() + "','" + matcher.group(7).toUpperCase().trim() + "');");
                }
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
