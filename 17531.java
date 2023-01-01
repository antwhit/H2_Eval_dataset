import vrml.*;
import vrml.node.*;
import vrml.field.*;
import java.util.Random;

public class Water extends Script {

    private int xDimension;

    private int zDimension;

    private SFTime time;

    private SFNode shape;

    private BaseNode[] elevationGrid;

    private MFFloat height;

    private Random rand;

    float[] values;

    private float[] oldValues;

    private int frames;

    private int frame;

    public void initialize() {
        System.out.println("Water - init...");
        xDimension = (int) ((SFInt32) getField("xDimension")).getValue();
        zDimension = (int) ((SFInt32) getField("zDimension")).getValue();
        shape = (SFNode) getField("shape");
        time = (SFTime) getEventIn("time");
        frames = (int) ((SFInt32) getField("frames")).getValue();
        frame = frames;
        values = new float[xDimension * zDimension];
        oldValues = new float[xDimension * zDimension];
        for (int i = 0; i < values.length; i++) {
            values[i] = 0;
        }
        String syntax = "ElevationGrid { xDimension " + xDimension + " zDimension " + zDimension + " creaseAngle 3.14 " + " height [ ";
        for (int i = 1; i < zDimension * xDimension; ++i) {
            syntax = syntax + "0,";
        }
        syntax = syntax + "0 ] }";
        Browser browser = getBrowser();
        System.out.println("Water - init");
        try {
            elevationGrid = browser.createVrmlFromString(syntax);
            height = (MFFloat) ((Node) elevationGrid[0]).getEventIn("set_height");
            Node Shape = (Node) shape.getValue();
            SFNode geometry = (SFNode) Shape.getExposedField("geometry");
            geometry.setValue(elevationGrid[0]);
            rand = new Random();
        } catch (Exception e) {
            System.out.println(syntax);
            e.printStackTrace();
        }
    }

    public void processEvent(Event ev) {
        float[] output = new float[xDimension * zDimension];
        if (frame == frames) {
            for (int i = 0; i < values.length; i++) {
                oldValues[i] = values[i];
                if (i > xDimension && i % xDimension == 0) {
                    values[i] = values[i - xDimension];
                } else if (i > zDimension && i % zDimension == 0) {
                    values[i] = values[i - zDimension];
                } else {
                    values[i] = rand.nextFloat();
                }
            }
            frame = 0;
            output = oldValues;
        }
        for (int i = 0; i < values.length; i++) {
            output[i] = (values[i] - oldValues[i]) * frame / frames + oldValues[i];
        }
        frame++;
        height.setValue(output);
    }
}
