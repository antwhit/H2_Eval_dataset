import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fr.esrf.tangoatk.widget.util.chart.*;

public class ChartExample4 extends JFrame implements IJLChartListener {

    final JLDataView yData;

    public ChartExample4() {
        final JLChart chart = new JLChart();
        final int nbValue = 50;
        chart.setHeaderFont(new Font("Times", Font.BOLD, 18));
        chart.setHeader("Spectrum Monitoring");
        chart.setJLChartListener(this);
        chart.getY1Axis().setName("dB");
        chart.getY1Axis().setAutoScale(true);
        chart.getXAxis().setAutoScale(true);
        chart.getXAxis().setName("Frequency (Hz)");
        chart.getXAxis().setAnnotation(JLAxis.VALUE_ANNO);
        yData = new JLDataView();
        yData.setName("Amplitude");
        yData.setUnit("dB");
        yData.setColor(new Color(200, 0, 0));
        yData.setLineWidth(2);
        chart.getY1Axis().addDataView(yData);
        new Thread() {

            public void run() {
                while (true) {
                    yData.reset();
                    for (int i = 0; i < nbValue; i++) {
                        double v = 2 * Math.PI * ((double) i / 20.0);
                        yData.add((double) i * 50.0, Math.abs(Math.sin(v) * Math.exp(-(double) i / 10.0) * (1.0 + Math.random() / 5.0)));
                    }
                    chart.repaint();
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
        setTitle("Chart Example 4");
        setVisible(true);
    }

    public String[] clickOnChart(JLChartEvent e) {
        String[] ret = new String[2];
        ret[0] = "Frequency= " + e.getTransformedXValue() + " Hz";
        ret[1] = "Amplitude= " + e.getTransformedYValue() + " " + yData.getUnit();
        return ret;
    }

    public static void main(String[] args) {
        final ChartExample4 f = new ChartExample4();
    }
}
