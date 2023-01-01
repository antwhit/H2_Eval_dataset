import eprog.*;
import java.util.StringTokenizer;

public class det23 extends EprogIO {

    public static void main(String[] args) {
        boolean bInputError = false;
        boolean bInputFormatError = false;
        int iDimension;
        int iA[][] = null;
        try {
            iDimension = readInt();
        } catch (EprogException e) {
            bInputFormatError = true;
            iDimension = 4;
        }
        if (iDimension != 2 && iDimension != 3) {
            bInputError = true;
        }
        if (bInputFormatError || bInputError) {
        } else {
            int iX, iY;
            iA = new int[iDimension][];
            for (iY = 0; iY < iDimension; iY++) {
                iA[iY] = new int[iDimension];
                for (iX = 0; iX < iDimension; iX++) {
                    try {
                        iA[iY][iX] = readInt();
                    } catch (EprogException e) {
                        bInputFormatError = true;
                    }
                }
            }
        }
        if (bInputFormatError) {
            println("?");
            return;
        } else if (bInputError) {
            println("FALSCHE EINGABE");
            return;
        }
        int iDet;
        if (iDimension == 2) iDet = iA[0][0] * iA[1][1] - iA[0][1] * iA[1][0]; else iDet = iA[0][0] * iA[1][1] * iA[2][2] + iA[0][1] * iA[1][2] * iA[2][0] + iA[0][2] * iA[1][0] * iA[2][1] - iA[0][2] * iA[1][1] * iA[2][0] - iA[0][1] * iA[1][0] * iA[2][2] - iA[0][0] * iA[1][2] * iA[2][1];
        println(iDet);
        return;
    }
}
