import pspdash.data.Correlation;
import pspdash.data.LinearRegression;
import java.io.PrintWriter;
import java.util.Vector;

class RegressionMethod extends Method {

    LinearRegression l;

    Correlation c;

    public RegressionMethod(HistData data, double estObjLOC, int xCol, int yCol, String letter, String purpose) {
        super(data, estObjLOC, letter, purpose);
        Vector dataPoints = data.getXYDataPoints(xCol, yCol);
        l = new LinearRegression(dataPoints);
        c = new Correlation(dataPoints);
        if (dataPoints.size() < 3 || badDouble(l.beta0) || badDouble(l.beta1) || badDouble(c.r) || badDouble(c.p)) {
            errorMessages.add("You do not have enough historical data.");
            rating = CANNOT_CALCULATE;
            return;
        }
        l.project(estObjLOC, 0.70);
        rateBetas(true, l.beta0, l.projection, l.beta1, getExpectedBeta1(), getExpectedBeta1Text());
        if (rating > 0) {
            observations.add("Your regression parameters are within bounds (" + BETA0 + " = " + formatNumber(l.beta0) + ", " + BETA1 + " = " + formatBeta1(l.beta1) + ").");
            rateCorrelation();
        }
    }

    public void rateCorrelation() {
        double rSquared = c.r * c.r;
        String statement = "(" + RSQ + " = " + formatNumber(rSquared) + ").";
        if (rSquared > 0.9) {
            observations.add("In addition, the historical data points have an excellent " + "correlation " + statement);
            rating += 2;
        } else if (rSquared > 0.7) {
            observations.add("In addition, the historical data points have a strong " + "correlation " + statement);
            rating += 1;
        } else if (rSquared > 0.5) {
            observations.add("The historical data points have an adequate correlation " + statement);
        } else {
            rating = SERIOUS_PROBLEM;
            errorMessages.add("The correlation between your historical data points is not " + "reliable for planning purposes " + statement);
            return;
        }
        statement = "(" + PROB + " = " + formatNumber(c.p * 100) + "%)";
        if (c.p <= 0.05) {
            observations.add("This correlation appears to be significant " + statement);
            rating += 0.5;
        } else if (c.p <= 0.20) {
            observations.add("This correlation is probably significant " + statement);
            rating += 0.2;
        } else if (c.p <= 0.20) {
            observations.add("This correlation is likely to be significant " + statement);
        } else {
            observations.add("However, this correlation may not be significant " + statement);
            rating -= 0.5;
        }
    }

    public static final String RSQ = "<a href='params.htm' " + probe.LINK_ATTRS + ">r<sup>2</sup></a>";

    public static final String PROB = "<a href='params.htm' " + probe.LINK_ATTRS + ">p</a>";

    void printOption(PrintWriter out, boolean isSelected) {
        printOption(out, l.projection, isSelected, l.beta0, l.beta1, l.range, 0.70, (c.r * c.r));
    }

    void printTableRow(PrintWriter out, boolean isSelected) {
        printTableRow(out, l.projection, isSelected, l.beta0, l.beta1, l.range, (c.r * c.r), l.stddev);
    }
}
