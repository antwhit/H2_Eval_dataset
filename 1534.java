import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

class CompareKeyCommand extends OperationCommand implements ActionListener {

    CompareKeyCommand(AppMain app) {
        super(app);
    }

    public void execute(Fragment f, State s) throws OperationError {
        System.out.println("CompareKeyCommand");
        if (f instanceof Node) {
            Node n = (Node) f;
            n.compare_key(s);
        } else {
            throw new OperationError(AppText.not_for_fragment);
        }
    }
}

;
