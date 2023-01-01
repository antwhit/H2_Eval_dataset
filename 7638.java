import java.rmi.*;
import visad.*;
import visad.java2d.DisplayImplJ2D;
import visad.java3d.DisplayImplJ3D;

public class Test63 extends UISkeleton {

    private boolean twoD;

    boolean hasClientServerMode() {
        return false;
    }

    public Test63() {
    }

    public Test63(String[] args) throws RemoteException, VisADException {
        super(args);
    }

    public void initializeArgs() {
        twoD = false;
    }

    public int checkOption(String progName, char ch, String arg) {
        if (ch == '2') {
            twoD = true;
            return 1;
        }
        return 0;
    }

    DisplayImpl[] setupServerDisplays() throws RemoteException, VisADException {
        DisplayImpl[] dpys = new DisplayImpl[1];
        if (twoD) {
            dpys[0] = new DisplayImplJ2D("display1");
        } else {
            dpys[0] = new DisplayImplJ3D("display1");
        }
        return dpys;
    }

    void setupServerData(LocalDisplay[] dpys) throws RemoteException, VisADException {
        RealType ir_radiance = RealType.getRealType("ir_radiance");
        int size = 64;
        DisplayImpl display1 = (DisplayImpl) dpys[0];
        if (twoD) {
            RealType count = RealType.getRealType("count");
            FunctionType ir_histogram = new FunctionType(ir_radiance, count);
            FlatField histogram1 = FlatField.makeField(ir_histogram, size, false);
            System.out.print("Creating Java2D display...");
            display1.addMap(new ScalarMap(count, Display.YAxis));
            display1.addMap(new ScalarMap(ir_radiance, Display.XAxis));
            display1.addMap(new ConstantMap(0.0, Display.Red));
            display1.addMap(new ConstantMap(1.0, Display.Green));
            display1.addMap(new ConstantMap(0.0, Display.Blue));
            DataReferenceImpl ref_histogram1;
            ref_histogram1 = new DataReferenceImpl("ref_histogram1");
            ref_histogram1.setData(histogram1);
            display1.addReference(ref_histogram1, null);
        } else {
            RealType vis_radiance = RealType.getRealType("vis_radiance");
            RealType[] types = { RealType.Latitude, RealType.Longitude };
            RealType[] types2 = { vis_radiance, ir_radiance };
            RealTupleType earth_location = new RealTupleType(types);
            RealTupleType radiance = new RealTupleType(types2);
            FunctionType image_tuple = new FunctionType(earth_location, radiance);
            FlatField imaget1 = FlatField.makeField(image_tuple, size, false);
            System.out.print("Creating Java3D display...");
            display1.addMap(new ScalarMap(RealType.Latitude, Display.YAxis));
            display1.addMap(new ScalarMap(RealType.Longitude, Display.XAxis));
            display1.addMap(new ScalarMap(vis_radiance, Display.ZAxis));
            display1.addMap(new ScalarMap(vis_radiance, Display.Green));
            display1.addMap(new ConstantMap(0.5, Display.Blue));
            display1.addMap(new ConstantMap(0.5, Display.Red));
            GraphicsModeControl mode = display1.getGraphicsModeControl();
            mode.setPointSize(2.0f);
            mode.setPointMode(false);
            mode.setMissingTransparent(true);
            DataReferenceImpl ref_imaget1 = new DataReferenceImpl("ref_imaget1");
            ref_imaget1.setData(imaget1);
            display1.addReference(ref_imaget1, null);
        }
        RemoteDisplayImpl[] rdi = new RemoteDisplayImpl[1];
        rdi[0] = new RemoteDisplayImpl(display1);
        System.out.println("done.");
        try {
            RemoteServerImpl server = new RemoteServerImpl(rdi);
            Naming.rebind("///RemoteSlaveDisplayTest", server);
            System.out.println("RemoteServer bound in registry");
        } catch (Exception e) {
            System.out.println("Couldn't construct RMI server.  Make sure " + "rmiregistry is running first.");
            System.exit(1);
        }
    }

    String getFrameTitle() {
        return "Remote slave display server";
    }

    public String toString() {
        return " [-2d]: remote slave display server" + "\n\trun rmiregistry first" + "\n\tany number of clients may connect" + "\n\toptional parameter enables Java2D instead of Java3D";
    }

    public static void main(String[] args) throws RemoteException, VisADException {
        new Test63(args);
    }
}
