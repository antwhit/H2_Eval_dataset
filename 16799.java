import java.io.*;
import java.sql.*;
import java.util.Vector;
import com.c5corp.c5gps.*;
import com.c5corp.c5gps.filters.*;
import com.c5corp.c5dem.*;
import com.c5corp.c5utm.*;
import com.c5corp.c5Algorithms.*;

/**
 * Class to test a number of methods for the great wall treking
 * @author Brett Stalbaum
 *
 */
public class GreatWall3 {

    /** main
	 * @param args
	 */
    public static void main(String args[]) {
        File csv = new File("TRACKS/DATONG.csv");
        CsvTrackLogListFilter filter = new CsvTrackLogListFilter(csv);
        TrackLogList inputList = filter.readFile();
        TrackLog input = null;
        if (inputList.hasMoreElements()) {
            input = inputList.nextElement();
        }
        System.out.println(input);
        Connection connection = DbHelper.getDbConnection();
        UtmCoordinatePairElev[][] table = C5UTM.getCoordinatePairElevArray(10, 615000, 4145010, 1000, 1000, connection);
        int elevationDiff = input.calculateMinMaxElevationDifference();
        System.out.println(elevationDiff);
        int targetDistance = 900;
        UtmCoordinatePairElev[] startEnd = new UtmCoordinatePairElev[2];
        int backoff = 1;
        while (startEnd[0] == null && startEnd[1] == null && backoff <= 10) {
            startEnd = findStartAndEnd(table, elevationDiff, targetDistance, backoff);
            backoff++;
        }
        System.out.println("from: " + startEnd[0] + " to " + startEnd[1]);
        SlopeReductionHiker hiker = new SlopeReductionHiker(10, "T", startEnd[0], startEnd[1], 1000, 35, connection);
        VirtualHikerTrackLog tracklog = hiker.getTrack();
        if (!tracklog.slopeVetted()) {
            LcpHiker lcpHiker = new LcpHiker(10, "T", startEnd[0], startEnd[1], 1000, connection);
            tracklog = lcpHiker.getTrack(LcpHiker.MINIMIZE_CUMULATIVE_ELEVATION);
        }
        System.out.println(tracklog);
        File out = new File("TRACKS/otherJIAYUGUAN_SJ.csv");
        CsvTrackLogListFilter filterOut = new CsvTrackLogListFilter(out);
        Vector<TrackLog> veci = new Vector<TrackLog>();
        veci.add(tracklog);
        TrackLogList outputList = new TrackLogList(veci);
        filterOut.writeFile(outputList);
        DbHelper.closeDbConnection(connection);
    }

    private static UtmCoordinatePairElev[] findStartAndEnd(UtmCoordinatePairElev[][] table, int targetDifference, int targetDistance, int tolerence) {
        UtmCoordinatePairElev[] returnVal = new UtmCoordinatePairElev[2];
        int bestElevDifference = 1000000;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                for (int x = 0; x < table.length; x++) {
                    for (int y = 0; y < table[x].length; y++) {
                        int difference = table[i][j].getElevation() - table[x][y].getElevation();
                        double distance = getDistance(table[i][j], table[x][y]);
                        if (table[i][j] != table[x][y]) {
                            if (difference < targetDifference + tolerence && difference > targetDifference - tolerence && distance < (targetDistance + (tolerence * 30)) && distance > (targetDistance - (tolerence * 30))) {
                                if (bestElevDifference > difference) {
                                    returnVal[0] = table[x][y];
                                    returnVal[1] = table[i][j];
                                    bestElevDifference = difference;
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnVal;
    }

    /** getDistance is a utility method returning the distance between two UtmCoordinatePair objects.
	* @param one the first coordinate
	* @param two the first coordinate
	* @return the distance
	*/
    public static double getDistance(UtmCoordinatePair one, UtmCoordinatePair two) {
        int sw_e = (one.getEasting() < two.getEasting()) ? one.getEasting() : two.getEasting();
        int sw_n = (one.getNorthing() < two.getNorthing()) ? one.getNorthing() : two.getNorthing();
        int nw_n = (one.getNorthing() > two.getNorthing()) ? one.getNorthing() : two.getNorthing();
        int se_e = (one.getEasting() > two.getEasting()) ? one.getEasting() : two.getEasting();
        int side1 = nw_n - sw_n;
        int side2 = se_e - sw_e;
        return Math.sqrt(side1 * side1 + side2 * side2);
    }
}
