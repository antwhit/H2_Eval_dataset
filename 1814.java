/**A kozlekedesi lampa egyfajta kozlekedesi szabaly*/
public class TrafficLight extends StrictRule {

    /**Szamlalo arra hogy mennyi idonkent valt*/
    int tmp_counter = 5;

    /**Mennyi ideig legyen piros?*/
    int redCounter = 5;

    /**Mennyi ideig legyen zold?*/
    int greenCounter = 5;

    /**Mehetunk tovabb?*/
    boolean state;

    /**Default konstruktor*/
    public TrafficLight() {
    }

    /**Konstruktor*/
    public TrafficLight(String n, boolean s) {
        state = s;
        name = n;
        TestParser.Print("TrafficLight " + name + " created state: " + state);
    }

    /**a tovabbengedesi allapot atallitasa*/
    public void setstate(boolean s) {
        state = s;
    }

    /**Beallithatjuk hogy pontosan hol is van az adott lampa*/
    public void setactualRoadElement(RoadElement r) {
        actualRoadElement = r;
    }

    /**O lepteti a dolgokat, tole fugg a piros-zold idotartam*/
    public void step() {
        if (tmp_counter == 0) {
            if (state) {
                tmp_counter = greenCounter;
                state = !state;
            } else {
                tmp_counter = redCounter;
                state = !state;
            }
        }
        tmp_counter--;
    }

    /**Szabaly alkalmazasa StandardCar-ra*/
    public void applyRule(StandardCar sc) {
        if (state) {
            actualRoadElement.enterVehicle(sc);
        }
    }

    /**Szabaly alkalmazasa Rendor-re*/
    public void applyRule(Cops c) {
        if (state) {
            actualRoadElement.enterVehicle(c);
        }
    }

    /**Szabaly alkalmazasa BadGuy-ra*/
    public void applyRule(BadGuy b) {
        actualRoadElement.enterVehicle(b);
    }

    /**Szabaly alkalmazasa a nyulra*/
    public void applyRule(Rabbit r) {
        if (state) {
            actualRoadElement.enterVehicle(r);
        }
    }
}
