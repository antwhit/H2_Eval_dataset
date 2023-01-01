import java.io.Serializable;

@SuppressWarnings("serial")
public class SignalStatistic implements Serializable {

    private int strength = -1;

    private int quality = -1;

    private boolean locked = false;

    public SignalStatistic(String data) {
        parseData(data);
    }

    public int getStrength() {
        return strength;
    }

    public int getQuality() {
        return quality;
    }

    public boolean getLocked() {
        return locked;
    }

    private void parseData(String data) {
        try {
            data = data.substring("SIGNAL_DATA:".length(), data.length());
            String[] bits = data.split(",");
            if (bits.length == 3) {
                locked = "1".equals(bits[0].trim());
                strength = Integer.parseInt(bits[1].trim());
                quality = Integer.parseInt(bits[2].trim());
            }
        } catch (Exception e) {
            System.out.println("Error parsing signal statistic data.");
            e.printStackTrace();
        }
    }
}
