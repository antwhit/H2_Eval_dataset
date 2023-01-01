import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RadarPlot;
import org.jfree.data.CategoryDataset;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.PieDataset;

public class radar extends pspdash.CGIChartBase {

    /** Create a radar chart. */
    public JFreeChart createChart() {
        CategoryDataset catData = data.catDataSource();
        PieDataset pieData = null;
        if (catData.getRowCount() == 1) pieData = DatasetUtilities.createPieDatasetForRow(catData, 0); else pieData = DatasetUtilities.createPieDatasetForColumn(catData, 0);
        RadarPlot plot = new RadarPlot(pieData);
        JFreeChart chart = new JFreeChart(null, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        if (parameters.get("skipAxisLabels") != null) plot.setShowAxisLabels(false);
        String interiorGap = getParameter("interiorGap");
        if (interiorGap != null) try {
            plot.setInteriorGap(Integer.parseInt(interiorGap) / 100.0);
        } catch (NumberFormatException e) {
        }
        String interiorSpacing = getParameter("interiorSpacing");
        if (interiorSpacing != null) try {
            plot.setInteriorGap(Integer.parseInt(interiorSpacing) / 200.0);
        } catch (NumberFormatException e) {
        }
        return chart;
    }
}
