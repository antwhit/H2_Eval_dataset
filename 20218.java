import com.jrefinery.chart.*;

public class line extends pspdash.CGIChartBase {

    /** Create a  line chart. */
    public JFreeChart createChart() {
        JFreeChart chart = JFreeChart.createLineChart(data.catDataSource());
        setupCategoryChart(chart);
        return chart;
    }
}
