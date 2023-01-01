import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

class ExpandCommand extends OperationCommand implements ActionListener {

    ExpandCommand(AppMain app) {
        super(app);
    }

    public void execute(Fragment f, State s) throws OperationError {
        System.out.println("expand");
        if (f instanceof Subtree) {
            Subtree t = (Subtree) f;
            t.expand(s);
        } else {
            throw new OperationError(AppText.not_for_node);
        }
    }
}

;
