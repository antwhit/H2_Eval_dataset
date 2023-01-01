import java.rmi.RemoteException;
import visad.*;
import visad.java3d.DisplayImplJ3D;

public class Test04 extends TestSkeleton {

    public Test04() {
    }

    public Test04(String[] args) throws RemoteException, VisADException {
        super(args);
    }

    DisplayImpl[] setupServerDisplays() throws RemoteException, VisADException {
        DisplayImpl[] dpys = new DisplayImpl[1];
        dpys[0] = new DisplayImplJ3D("display", DisplayImplJ3D.APPLETFRAME);
        return dpys;
    }

    void setupServerData(LocalDisplay[] dpys) throws RemoteException, VisADException {
        RealType[] types = { RealType.Latitude, RealType.Longitude };
        RealTupleType earth_location = new RealTupleType(types);
        RealType vis_radiance = RealType.getRealType("vis_radiance");
        RealType ir_radiance = RealType.getRealType("ir_radiance");
        RealType[] types2 = { vis_radiance, ir_radiance };
        RealTupleType radiance = new RealTupleType(types2);
        FunctionType image_tuple = new FunctionType(earth_location, radiance);
        int size = 64;
        FlatField imaget1 = FlatField.makeField(image_tuple, size, false);
        dpys[0].addMap(new ScalarMap(RealType.Latitude, Display.Latitude));
        dpys[0].addMap(new ScalarMap(RealType.Longitude, Display.Longitude));
        dpys[0].addMap(new ScalarMap(vis_radiance, Display.RGB));
        GraphicsModeControl mode = dpys[0].getGraphicsModeControl();
        mode.setTextureEnable(false);
        DataReferenceImpl ref_imaget1 = new DataReferenceImpl("ref_imaget1");
        ref_imaget1.setData(imaget1);
        dpys[0].addReference(ref_imaget1, null);
    }

    public String toString() {
        return ": spherical coordinates";
    }

    public static void main(String[] args) throws RemoteException, VisADException {
        new Test04(args);
    }
}
