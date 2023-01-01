import objects.IMapGenerator;
import objects.Planet;
import objects.Position;
import objects.Race;
import objects.geom.MapGeometry;
import objects.production.AbstractProduction;
import objects.production.IProduction;
import util.SuperProperties;
import util.Utils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: serhiy
 * Created on Oct 31, 2006, 4:06:21 AM
 */
public class MapGen {

    private static final Logger logger = Logger.getLogger("ogs.maps");

    public static void main(String[] args) {
        Properties props = new SuperProperties();
        try {
            props.load(System.in);
        } catch (IOException err) {
            logger.log(Level.SEVERE, "Can't load properties", err);
            System.exit(1);
            return;
        }
        MapGeometry geometry = null;
        try {
            geometry = (MapGeometry) Class.forName(props.getProperty("Galaxy.MapGeometry")).newInstance();
            geometry.init(props);
            geometry.setSize(Double.parseDouble(props.getProperty("Galaxy.Size", "1")));
        } catch (Exception err) {
            logger.log(Level.SEVERE, "Can't initialize map geometry", err);
            System.exit(1);
        }
        Map<String, AbstractProduction> productions = new HashMap<String, AbstractProduction>();
        try {
            String[] availableProduction = Utils.split(props.getProperty("Galaxy.ProductionTypes"));
            for (int i = 0; i < availableProduction.length; ++i) {
                String key = availableProduction[i];
                AbstractProduction prod = (AbstractProduction) Class.forName(props.getProperty("Galaxy.ProductionType." + key)).newInstance();
                prod.setCode(i);
                productions.put(key, prod);
            }
        } catch (Exception err) {
            logger.log(Level.SEVERE, "Can't initialize production types", err);
            System.exit(1);
        }
        IMapGenerator mapGenerator;
        try {
            mapGenerator = (IMapGenerator) Class.forName(props.getProperty("Galaxy.MapGenerator")).newInstance();
            mapGenerator.extractData(props, geometry);
        } catch (Exception err) {
            logger.log(Level.SEVERE, "Can't initialize map generator", err);
            System.exit(1);
            return;
        }
        int racesCount = Integer.parseInt(props.getProperty("Galaxy.Races"));
        Race[] races = new Race[racesCount];
        Map<Race, Integer> raceCodes = new IdentityHashMap<Race, Integer>();
        for (int i = 0; i < races.length; ++i) {
            Race race = new Race(null, "Player_" + (i + 1), null);
            races[i] = race;
            raceCodes.put(race, i);
        }
        Planet[] planets = mapGenerator.generate(null, races);
        save(System.out, planets, raceCodes);
    }

    private static void save(OutputStream out, Planet[] planets, Map<Race, Integer> raceCodes) {
        PrintWriter writer = new PrintWriter(out);
        for (Planet planet : planets) {
            Position pos = planet.getPosition();
            Race owner = planet.getOwner();
            IProduction production = planet.getProduction();
            String productionName = production == null ? "-" : production.getName();
            println(writer, pos.getX(), pos.getY(), planet.getResources(), planet.getSize(), planet.getName(), planet.getPopulation(), planet.getIndustry(), planet.getFullEffort() - planet.getEffort(), productionName, productionName, planet.getNumber(), owner == null ? -1 : raceCodes.get(owner));
        }
        writer.flush();
    }

    private static void println(PrintWriter writer, Object... args) {
        for (Object arg : args) {
            writer.print(arg == null ? "null" : arg.toString());
            writer.print(' ');
        }
        writer.println();
    }
}
