import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Box;

public class Chair {

    public Appearance chairAppr;

    public Transform3D chairTrans3D;

    public Chair(Appearance appr, Transform3D t3d) {
        chairAppr = appr;
        chairTrans3D = t3d;
    }

    public TransformGroup Create() {
        Box Chairseat = new Box(0.5f, 0.1f, 0.5f, chairAppr);
        Box Chairback;
        TransformGroup chairTrans = new TransformGroup(chairTrans3D);
        Vector3d[] corners = new Vector3d[4];
        Vector3d chairbackVec3d = new Vector3d(0.0, 0.45, -0.45);
        TransformGroup[] legTrans = new TransformGroup[4];
        corners[0] = new Vector3d(0.47, -0.2, 0.47);
        corners[1] = new Vector3d(0.47, -0.2, -0.47);
        corners[2] = new Vector3d(-0.47, -0.2, -0.47);
        corners[3] = new Vector3d(-0.47, -0.2, 0.47);
        int i;
        for (i = 0; i < 4; i++) {
            chairTrans3D.set(corners[i]);
            legTrans[i] = new TransformGroup(chairTrans3D);
            chairTrans.addChild(legTrans[i]);
            legTrans[i].addChild(new Box(0.03f, 0.3f, 0.03f, chairAppr));
        }
        chairTrans3D.set(chairbackVec3d);
        TransformGroup chairbackTrans = new TransformGroup(chairTrans3D);
        chairTrans.addChild(chairbackTrans);
        Chairback = new Box(0.5f, 0.5f, 0.1f, chairAppr);
        chairbackTrans.addChild(Chairback);
        chairTrans.addChild(Chairseat);
        return (chairTrans);
    }
}
