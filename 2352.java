import visad.*;
import visad.java2d.DisplayImplJ2D;
import visad.data.netcdf.Plain;
import java.rmi.RemoteException;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleAnimate {

    public static void main(String args[]) throws VisADException, RemoteException, IOException {
        int step = 1000;
        if (args.length > 0) {
            try {
                step = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                step = 1000;
            }
        }
        if (step < 1) step = 1;
        Plain plain = new Plain();
        Field image_sequence = null;
        try {
            image_sequence = (Field) plain.open("images.nc");
        } catch (IOException exc) {
            String s = "To run this example, the images.nc file must be " + "present in\nyour visad/examples directory." + "You can obtain this file from:\n" + "  ftp://ftp.ssec.wisc.edu/pub/visad-2.0/images.nc.Z";
            System.out.println(s);
            System.exit(0);
        }
        final DataReference image_ref = new DataReferenceImpl("image");
        image_ref.setData(image_sequence);
        DisplayImpl display = new DisplayImplJ2D("image display");
        FunctionType image_sequence_type = (FunctionType) image_sequence.getType();
        FunctionType image_type = (FunctionType) image_sequence_type.getRange();
        RealTupleType domain_type = image_type.getDomain();
        display.addMap(new ScalarMap((RealType) domain_type.getComponent(0), Display.XAxis));
        display.addMap(new ScalarMap((RealType) domain_type.getComponent(1), Display.YAxis));
        display.addMap(new ScalarMap((RealType) image_type.getRange(), Display.RGB));
        RealType hour_type = (RealType) image_sequence_type.getDomain().getComponent(0);
        ScalarMap animation_map = new ScalarMap(hour_type, Display.Animation);
        display.addMap(animation_map);
        AnimationControl animation_control = (AnimationControl) animation_map.getControl();
        animation_control.setStep(step);
        animation_control.setOn(true);
        display.addReference(image_ref);
        JFrame frame = new JFrame("SimpleAnimate VisAD Application");
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        frame.getContentPane().add(panel);
        panel.add(display.getComponent());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
