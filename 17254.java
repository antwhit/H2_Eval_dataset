import com.c5corp.c5utm.*;
import java.util.Vector;
import java.sql.Connection;

/**
 * Driver program utilized to discover "Other Locations"
 * C5 Landscape Database API 2.0 example
 * Brett Stalbaum, 2005
 */
public class FindSimilar {

    /** main
	 * @param args
	 */
    public static void main(String[] args) {
        Connection connection = DbHelper.getDbConnection();
        StatisticalPoint statPoint = C5UTM.getStatisticalPoint(11, 565140, 3645660, connection);
        System.out.println("Input point:");
        System.out.println(statPoint);
        System.out.println();
        Points points = C5UTM.getPoints(statPoint.getZone(), statPoint.getEasting() - 480, statPoint.getNorthing() - 480, 1000, 1000, connection);
        System.out.println("The corresponding Points object:");
        System.out.println(points);
        System.out.println();
        UtmImage image = new UtmImage(points, UtmImage.RENDER_HIGH | UtmImage.RENDER_LOW | UtmImage.RENDER_MEAN | UtmImage.RENDER_MEDIAN | UtmImage.RENDER_MODE, false);
        image.writeImageFile("", "c5utm_similarity_input_point");
        Vector<StatisticalPoint> results = C5UTM.findSimilarPoints(statPoint, 1, connection);
        for (int i = 0; i < results.size(); i++) {
            System.out.println("Result " + (i + 1) + ":");
            System.out.println(results.get(i));
            System.out.println();
            points = C5UTM.getPoints(results.get(i).getZone(), results.get(i).getEasting() - 480, results.get(i).getNorthing() - 480, 1000, 1000, connection);
            image = new UtmImage(points, UtmImage.RENDER_HIGH | UtmImage.RENDER_LOW | UtmImage.RENDER_MEAN | UtmImage.RENDER_MEDIAN | UtmImage.RENDER_MODE, false);
            image.writeImageFile("", "c5utm_img_" + (i + 1));
        }
        DbHelper.closeDbConnection(connection);
    }
}
