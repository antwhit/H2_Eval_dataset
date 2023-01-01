import java.util.ArrayList;

public class Timer {

    /**A city, amiben mogatjuk az idot*/
    private City city;

    /**Taroljuk a lampakat, mivel ott kituntetett figyelem van az idozitesen*/
    private ArrayList<TrafficLight> trafficLights = new ArrayList<TrafficLight>();

    public void setCity(City c) {
        city = c;
    }

    /**Ha letelt a megfelelo mennyisegu ido akkor Tick-elunk egyet amely eloreviszi
  a jatekunkban az idot*/
    public void Tick() {
        SingletonPrinter.Print(true, "Timer|Tick()");
        city.stepAll();
        SingletonPrinter.Print(false, "Timer|Tick()");
        for (int i = 0; i < trafficLights.size(); i++) {
            trafficLights.get(i).step();
        }
    }
}
