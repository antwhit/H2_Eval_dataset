import palmutils.pdb.*;
import java.io.*;

public class AstroRecord extends PDBElement {

    public static final int DESCRIPTIONSIZE = 32;

    String description;

    double ra;

    double dec;

    double magnitude;

    double sizex;

    double sizey;

    public String toString() {
        return "[" + description + ": (" + (ra * 12 / Math.PI) + "),(" + (dec * 180 / Math.PI) + ")," + magnitude + "," + sizex + "x" + sizey + "]" + "{" + getSize() + "}";
    }

    public int getSize() {
        return 14 + (description == null ? 1 : description.length() < DESCRIPTIONSIZE ? description.length() + 1 : DESCRIPTIONSIZE);
    }

    public void write(DataOutput out) throws IOException {
        if (sizex > 3270.0 || sizey > 3270.0 || magnitude > 327.0 || sizex < 0 || sizey < 0 || magnitude < -327.0) throw new IllegalArgumentException(toString());
        out.writeInt((int) (long) (ra * (1L << 31) / Math.PI));
        out.writeInt((int) (long) (dec * (1L << 31) / Math.PI));
        out.writeShort((int) (magnitude * 100));
        out.writeShort((int) (sizex * 10));
        out.writeShort((int) (sizey * 10));
        writePackedString(out, description, 32);
    }

    public void read(DataInput in) throws IOException {
        ra = Math.PI / (1 << 31) * in.readInt();
        in.readInt();
        dec = Math.PI / (1 << 31) * in.readInt();
        in.readInt();
        magnitude = in.readShort() / 100.0;
        sizex = in.readShort() / 100.0;
        sizey = in.readShort() / 100.0;
        description = readPackedString(in, 32);
    }

    public AstroRecord() {
    }

    public AstroRecord(DataInput in) throws IOException {
        read(in);
    }
}
