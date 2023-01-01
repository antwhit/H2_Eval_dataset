import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NorthPanel extends JPanel {

    private CornerLot FreeParking;

    private PropertyLot KentuckyAve;

    private CardDrawLot Chance;

    private PropertyLot IndianaAve;

    private PropertyLot IllinoisAve;

    private RailroadLot BandORailroad;

    private PropertyLot AtlanticAve;

    private PropertyLot VentnorAve;

    private UtilityLot WaterWorks;

    private PropertyLot MarvinGardens;

    private CornerLot GoToJail;

    public NorthPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.white);
        FreeParking = new CornerLot("FreeParking", "corner", "northwest");
        KentuckyAve = new PropertyLot("KentuckyAve", "property", "north", 220, Color.red, 18, 90, 250, 700, 875, 1050, 110, 150, 150);
        Chance = new CardDrawLot("ChanceNorth", "cardDraw", "north", "Chance", 2);
        IndianaAve = new PropertyLot("IndianaAve", "property", "north", 220, Color.red, 18, 90, 250, 700, 875, 1050, 110, 150, 150);
        IllinoisAve = new PropertyLot("IllinoisAve", "property", "north", 240, Color.red, 20, 100, 300, 750, 925, 1100, 120, 150, 150);
        BandORailroad = new RailroadLot("B&ORailroad", "railroad", "north");
        AtlanticAve = new PropertyLot("AtlanticAve", "property", "north", 260, Color.yellow, 22, 110, 330, 800, 975, 1150, 130, 150, 150);
        VentnorAve = new PropertyLot("VentnorAve", "property", "north", 260, Color.yellow, 22, 110, 330, 800, 975, 1150, 130, 150, 150);
        WaterWorks = new UtilityLot("WaterWorks", "utility", "north");
        MarvinGardens = new PropertyLot("MarvinGardens", "property", "north", 280, Color.yellow, 24, 120, 360, 850, 1025, 1200, 140, 150, 150);
        GoToJail = new CornerLot("GoToJail", "corner", "northeast");
        add(FreeParking);
        add(KentuckyAve);
        add(Chance);
        add(IndianaAve);
        add(IllinoisAve);
        add(BandORailroad);
        add(AtlanticAve);
        add(VentnorAve);
        add(WaterWorks);
        add(MarvinGardens);
        add(GoToJail);
    }

    public void buildHouses(int num, String name) {
        if (name == "KentuckyAve") {
            KentuckyAve.setNumHouses(num);
        } else if (name == "IndianaAve") {
            IndianaAve.setNumHouses(num);
        } else if (name == "IllinoisAve") {
            IllinoisAve.setNumHouses(num);
        } else if (name == "AtlanticAve") {
            AtlanticAve.setNumHouses(num);
        } else if (name == "VentnorAve") {
            VentnorAve.setNumHouses(num);
        } else if (name == "MarvinGardens") {
            MarvinGardens.setNumHouses(num);
        }
    }

    public void removeAllHouses(String name) {
        if (name == "KentuckyAve") {
            KentuckyAve.setNumHouses(0);
        } else if (name == "IndianaAve") {
            IndianaAve.setNumHouses(0);
        } else if (name == "IllinoisAve") {
            IllinoisAve.setNumHouses(0);
        } else if (name == "AtlanticAve") {
            AtlanticAve.setNumHouses(0);
        } else if (name == "VentnorAve") {
            VentnorAve.setNumHouses(0);
        } else if (name == "MarvinGardens") {
            MarvinGardens.setNumHouses(0);
        }
    }

    public void buildHotel(String name) {
        if (name == "KentuckyAve") {
            KentuckyAve.setNumHotels(1);
        } else if (name == "IndianaAve") {
            IndianaAve.setNumHotels(1);
        } else if (name == "IllinoisAve") {
            IllinoisAve.setNumHotels(1);
        } else if (name == "AtlanticAve") {
            AtlanticAve.setNumHotels(1);
        } else if (name == "VentnorAve") {
            VentnorAve.setNumHotels(1);
        } else if (name == "MarvinGardens") {
            MarvinGardens.setNumHotels(1);
        }
    }
}
