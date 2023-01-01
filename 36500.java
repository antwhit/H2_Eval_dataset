import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GenerateSensitivityData {

    private static final String safetiesDataFilename = "safeties.data";

    private static final Object[][] safeties = new Object[][] { { "e-mail", new Float(0.40) }, { "home-address", new Float(0.14) }, { "work-address", new Float(0.20) }, { "name", new Float(0.64) }, { "sex", new Float(0.68) }, { "age", new Float(0.52) }, { "profession", new Float(0.35) }, { "education", new Float(0.52) }, { "income", new Float(0.05) }, { "telephone", new Float(0.13) }, { "medical-data", new Float(0.06) }, { "bank-account", new Float(0.07) }, { "credit-card-number", new Float(0.08) }, { "credit-card-crc", new Float(0.03) }, { "credit-card-expdate", new Float(0.14) }, { "credit-card-startdate", new Float(0.15) }, { "credit-card-name", new Float(0.08) }, { "location", new Float(0.10) }, { "symLoc", new Float(0.10) }, { "status", new Float(0.15) }, { "activity", new Float(0.20) }, { "lastPrivacyAction", new Float(0.02) }, { "lastUserAction", new Float(0.05) }, { "preferenceRegistry", new Float(0.01) }, { "privacyPreferenceRegistry", new Float(0.01) }, { "uid", new Float(0.01) }, { "groupingTable", new Float(0.01) }, { "agendaActivity", new Float(0.34) }, { "SHARE_WITH_3RD_PARTIES", new Float(0.80) }, { "DATA_RETENTION_IN_HOURS", new Float(0.60) }, { "RIGHT_TO_OPTOUT", new Float(0.70) }, { "STORE_IN_SECURE_STORAGE", new Float(0.90) }, { "RIGHT_TO_ACCESS_HELD_DATA", new Float(0.80) }, { "RIGHT_TO_CORRECT_INCORRECT_DATA", new Float(0.80) } };

    public static void main(String[] args) throws Exception {
        Map<String, Float> map = new HashMap<String, Float>();
        for (int i = 0; i < safeties.length; i++) map.put((String) safeties[i][0], (Float) safeties[i][1]);
        save(map, safetiesDataFilename);
    }

    private static void save(Object obj, String filename) throws Exception {
        File file = new File(filename);
        file.delete();
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream renderDataStream = new ObjectOutputStream(fos);
        renderDataStream.writeObject(obj);
        renderDataStream.flush();
        renderDataStream.close();
    }
}
