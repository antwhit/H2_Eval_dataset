import java.awt.Font;
import com.jrefinery.chart.*;
import com.jrefinery.chart.RadarPlot;

public class radar extends pspdash.CGIChartBase {

    /** Create a  line chart. */
    public JFreeChart createChart() {
        if (data.numCols() == 1) data = data.transpose();
        Plot plot = null;
        try {
            plot = new RadarPlot(null);
        } catch (AxisNotCompatibleException ance) {
            return null;
        }
        JFreeChart chart = new JFreeChart("Radar Chart", new Font("Arial", Font.BOLD, 24), data.catDataSource(), plot);
        chart.setLegend(null);
        if (parameters.get("skipAxisLabels") != null) ((RadarPlot) plot).setDrawAxisLabels(false);
        return chart;
    }
}
