import java.lang.reflect.Method;

public class AMFService {

    public Method[] list() {
        return this.getClass().getMethods();
    }

    public String echo(String message) {
        String msg = "AMFServer echo : ";
        msg += message;
        return msg;
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
