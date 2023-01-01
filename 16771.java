import java.util.ArrayList;

public class Timer {

    /**Hanyat utott mar az ora?*/
    int count = 0;

    /**Konstruktor*/
    public Timer() {
        TestParser.Print("Timer Created!");
    }

    /**A city, amiben mogatjuk az idot*/
    private City city;

    /**Taroljuk a lampakat, mivel ott kituntetett figyelem van az idozitesen*/
    private ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();

    /**Ctiy beallitasa*/
    public void setCity(City c) {
        city = c;
    }

    /**Ha letelt a megfelelo mennyisegu ido akkor Tick-elunk egyet amely eloreviszi
  a jatekunkban az idot*/
    public void Tick() {
        count++;
        TestParser.Print("Timer ticks " + count + " times!");
        city.stepAll();
        for (int i = 0; i < trafficLights.size(); i++) {
            trafficLights.get(i).step();
        }
    }
}
