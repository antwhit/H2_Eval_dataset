import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import java.util.*;

public class Session {

    private Server server;

    private String parameters;

    public Session(Server server, String parameters) {
        this.server = server;
        this.parameters = parameters;
    }

    public Server getServer() {
        return server;
    }

    public String getParameters() {
        return parameters;
    }
}
