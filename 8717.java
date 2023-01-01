import com.jrefinery.chart.*;
import com.jrefinery.chart.PiePlot;

public class pie extends pspdash.CGIChartBase {

    /** Create a  line chart. */
    public JFreeChart createChart() {
        if (data.numCols() == 1) data = data.transpose();
        JFreeChart chart = JFreeChart.createPieChart(data.catDataSource());
        PiePlot plot = (PiePlot) chart.getPlot();
        if (parameters.get("skipWedgeLabels") != null) plot.setDrawWedgeLabels(false); else if (parameters.get("wedgeLabelFontSize") != null) try {
            float fontSize = Float.parseFloat(getParameter("wedgeLabelFontSize"));
            plot.setWedgeLabelFont(plot.getWedgeLabelFont().deriveFont(fontSize));
        } catch (Exception lfe) {
        }
        if (parameters.get("ellipse") != null) plot.setDrawCircle(false);
        String interiorSpacing = getParameter("interiorSpacing");
        if (interiorSpacing != null) try {
            plot.setInteriorSpacing(Integer.parseInt(interiorSpacing));
        } catch (NumberFormatException e) {
        }
        return chart;
    }
}
