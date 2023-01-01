import java.awt.*;

public abstract class Demo1App extends java.applet.Applet {

    abstract String[] getNames();

    abstract Object[] getWidgets();

    public void init() {
        System.out.println("My init");
    }

    public boolean handleEvent(Event event) {
        System.out.println("Unhandled event: " + event);
        return false;
    }

    public void my_action(String s) {
        System.out.println("my action: " + s);
    }
}
