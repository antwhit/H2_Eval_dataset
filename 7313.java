import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fr.esrf.tangoatk.widget.util.chart.*;

public class ChartExample3 extends JFrame {

    public ChartExample3() {
        final JLChart chart = new JLChart();
        final JLDataView xData;
        final JLDataView yData1;
        final double startTime;
        chart.setHeaderFont(new Font("Times", Font.BOLD, 18));
        chart.setHeader("Real time XY Monitoring");
        chart.setDisplayDuration(60000);
        chart.getY1Axis().setName("X value");
        chart.getY1Axis().setAutoScale(true);
        chart.getXAxis().setAutoScale(true);
        chart.getXAxis().setName("Y value");
        yData1 = new JLDataView();
        yData1.setName("XY plot1");
        yData1.setColor(new Color(200, 0, 0));
        yData1.setMarkerColor(new Color(100, 0, 0));
        yData1.setLineWidth(1);
        yData1.setMarker(JLDataView.MARKER_DOT);
        yData1.setMarkerSize(4);
        chart.getY1Axis().addDataView(yData1);
        xData = new JLDataView();
        xData.setName("X view");
        chart.getXAxis().addDataView(xData);
        startTime = (double) (System.currentTimeMillis()) / 1000.0;
        new Thread() {

            public void run() {
                while (true) {
                    double now = (double) (System.currentTimeMillis()) / 1000.0;
                    double t1 = 2 * Math.PI * ((now - startTime) / 30.0);
                    double t2 = 2 * Math.PI * ((now - startTime) / 300.0);
                    chart.addData(xData, now * 1000.0, Math.sin(t1) * Math.sin(t2));
                    chart.addData(yData1, now * 1000.0, Math.sin(t1 + 2.0) * Math.sin(t2));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
        JPanel bot = new JPanel();
        bot.setLayout(new FlowLayout());
        JButton b = new JButton("Exit");
        b.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        bot.add(b);
        JButton c = new JButton("Graph options");
        c.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                chart.showOptionDialog();
            }
        });
        bot.add(c);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(chart, BorderLayout.CENTER);
        getContentPane().add(bot, BorderLayout.SOUTH);
        setSize(640, 480);
        setTitle("Chart Example 3");
        setVisible(true);
    }

    public static void main(String[] args) {
        final ChartExample3 f = new ChartExample3();
    }
}
