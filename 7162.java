import java.awt.Component;
import java.rmi.RemoteException;
import visad.*;
import visad.java3d.DisplayImplJ3D;

public class Test65 extends UISkeleton {

    public Test65() {
    }

    public Test65(String[] args) throws RemoteException, VisADException {
        super(args);
    }

    DisplayImpl[] setupServerDisplays() throws RemoteException, VisADException {
        DisplayImpl[] dpys = new DisplayImpl[1];
        dpys[0] = new DisplayImplJ3D("display1", DisplayImplJ3D.APPLETFRAME);
        return dpys;
    }

    void setupServerData(LocalDisplay[] dpys) throws RemoteException, VisADException {
        RealType[] types3d = { RealType.Latitude, RealType.Longitude, RealType.Radius };
        RealTupleType earth_location3d = new RealTupleType(types3d);
        RealType vis_radiance = RealType.getRealType("vis_radiance");
        RealType ir_radiance = RealType.getRealType("ir_radiance");
        RealType[] types2 = { vis_radiance, ir_radiance };
        RealTupleType radiance = new RealTupleType(types2);
        FunctionType grid_tuple = new FunctionType(earth_location3d, radiance);
        int size3d = 6;
        float level = 2.5f;
        FlatField grid3d = FlatField.makeField(grid_tuple, size3d, false);
        dpys[0].addMap(new ScalarMap(RealType.Latitude, Display.YAxis));
        dpys[0].addMap(new ScalarMap(RealType.Longitude, Display.XAxis));
        dpys[0].addMap(new ScalarMap(RealType.Radius, Display.ZAxis));
        dpys[0].addMap(new ScalarMap(ir_radiance, Display.Green));
        dpys[0].addMap(new ConstantMap(0.5, Display.Blue));
        dpys[0].addMap(new ConstantMap(0.5, Display.Red));
        ScalarMap map1contour = new ScalarMap(vis_radiance, Display.IsoContour);
        dpys[0].addMap(map1contour);
        dpys[0].addMap(new ScalarMap(RealType.Latitude, Display.SelectRange));
        dpys[0].addMap(new ScalarMap(RealType.Longitude, Display.SelectRange));
        DataReferenceImpl ref_grid3d = new DataReferenceImpl("ref_grid3d");
        ref_grid3d.setData(grid3d);
        dpys[0].addReference(ref_grid3d, null);
        ContourControl cc = (ContourControl) map1contour.getControl();
        cc.setSurfaceValue(2.5f);
    }

    String getFrameTitle() {
        return "VisAD widget panel test";
    }

    Component getSpecialComponent(LocalDisplay[] dpys) throws RemoteException, VisADException {
        return dpys[0].getWidgetPanel();
    }

    public String toString() {
        return ": test VisAD widget panel";
    }

    public static void main(String[] args) throws RemoteException, VisADException {
        new Test65(args);
    }
}
