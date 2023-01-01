import ij.ImageJ;
import ij.ImagePlus;
import ijaux.*;
import ijaux.hypergeom.*;
import ijaux.hypergeom.index.BaseIndex;
import ijaux.hypergeom.morphology.*;
import ijaux.iter.seq.*;

public class ErosionTest {

    PixLib plib = new PixLib();

    @SuppressWarnings("unchecked")
    public ErosionTest() {
        float radius = 1.0f;
        int ndim = 3;
        int[] dim = { 100, 100, 10 };
        byte[] pixels_byte = Util.rampByte(100000, 100);
        PixelCube<Byte, BaseIndex> cube = new PixelCube<Byte, BaseIndex>(dim, pixels_byte, BaseIndex.class);
        StructureElement<Byte> se = new StructureElement<Byte>(radius, ndim, Byte.class);
        se.createDiamondMask(radius, false);
        PixelCube<Byte, BaseIndex> pc = (PixelCube<Byte, BaseIndex>) se.getMask();
        System.out.println("Structure element:");
        System.out.println(se.getVect());
        ImagePlus imp4 = plib.imageFrom("test", cube);
        imp4.show();
        ImagePlus imp2 = plib.imageFrom("diamond SE", pc);
        imp2.show();
        MorphoProcessorXD<Byte> mproc = new MorphoProcessorXD<Byte>(se);
        Region<Byte> reg = new Region<Byte>(cube, se);
        System.out.println("Region:");
        Region<Byte> regout = mproc.erode(reg);
        ImagePlus imp3 = plib.imageFrom("eroded", regout);
        imp3.show();
    }

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        try {
            System.setProperty("plugins.dir", args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ImageJ();
        new ErosionTest();
    }
}
