import java.rmi.RemoteException;
import visad.*;
import visad.java2d.DisplayImplJ2D;

public class Test36 extends UISkeleton {

    public Test36() {
    }

    public Test36(String[] args) throws RemoteException, VisADException {
        super(args);
    }

    DisplayImpl[] setupServerDisplays() throws RemoteException, VisADException {
        DisplayImpl[] dpys = new DisplayImpl[1];
        dpys[0] = new DisplayImplJ2D("display");
        return dpys;
    }

    void setupServerData(LocalDisplay[] dpys) throws RemoteException, VisADException {
        Unit super_degree = CommonUnit.degree.scale(2.5);
        RealType lon = RealType.getRealType("lon", super_degree);
        RealType radius = RealType.getRealType("radius");
        RealType x = RealType.getRealType("x");
        RealType y = RealType.getRealType("y");
        RealType[] types = { RealType.Latitude, RealType.Longitude };
        RealTupleType earth_location = new RealTupleType(types);
        RealType vis_radiance = RealType.getRealType("vis_radiance");
        RealType ir_radiance = RealType.getRealType("ir_radiance");
        RealType[] types2 = { vis_radiance, ir_radiance };
        RealTupleType radiance = new RealTupleType(types2);
        FunctionType image_tuple = new FunctionType(earth_location, radiance);
        int size = 64;
        FlatField imaget1 = FlatField.makeField(image_tuple, size, false);
        dpys[0].addMap(new ScalarMap(RealType.Latitude, Display.Radius));
        ScalarMap lonmap = new ScalarMap(RealType.Longitude, Display.Longitude);
        lonmap.setRangeByUnits();
        dpys[0].addMap(lonmap);
        dpys[0].addMap(new ScalarMap(vis_radiance, Display.RGB));
        DataReferenceImpl ref_imaget1 = new DataReferenceImpl("ref_imaget1");
        ref_imaget1.setData(imaget1);
        dpys[0].addReference(ref_imaget1, null);
    }

    String getFrameTitle() {
        return "polar coordinates in Java2D";
    }

    public String toString() {
        return ": polar coordinates in Java2D";
    }

    public static void main(String[] args) throws RemoteException, VisADException {
        new Test36(args);
    }
}
