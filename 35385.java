import java.io.PrintWriter;

public class ClickOutput {

    public static int printClick(PrintWriter out, int count) {
        out.print(++count + "-e ���������" + "<br>");
        return count;
    }
}
