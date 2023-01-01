import java.util.ArrayList;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class Function extends Task {

    private String function;

    private int startSize;

    private int maxSize;

    private int stepSize;

    private int currentSize;

    public Function(String function, int startSize, int stepSize, int maxSize) {
        this.function = function;
        this.startSize = startSize;
        this.stepSize = stepSize;
        this.maxSize = maxSize;
        this.currentSize = startSize;
    }

    @Override
    public ArrayList<TimeEvent> run() {
        ArrayList<TimeEvent> results = new ArrayList<TimeEvent>();
        while (this.currentSize <= this.maxSize) {
            TimeEvent event = new TimeEvent((long) this.currentSize, (long) calculateNextYValue());
            results.add(event);
            int progress = this.currentSize * 100 / (this.maxSize - this.startSize);
            writeProgressToFile(progress);
            currentSize++;
        }
        return results;
    }

    private double calculateNextYValue() {
        String expression = this.function;
        int xvalue = this.currentSize;
        Calculable calc = null;
        try {
            calc = new ExpressionBuilder(expression).withVariable("x", xvalue).build();
        } catch (UnknownFunctionException e) {
            e.printStackTrace();
        } catch (UnparsableExpressionException e) {
            e.printStackTrace();
        }
        double result1 = calc.calculate();
        return result1;
    }

    @Override
    public String getType() {
        return "function";
    }

    public String getExpression() {
        return this.function;
    }
}
