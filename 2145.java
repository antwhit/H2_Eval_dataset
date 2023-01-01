import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.awt.image.*;

public class UMClipObj implements Transferable, ClipboardOwner {

    public void main() {
        System.out.println("UMClipObj.main(" + ")");
    }

    private static final DataFlavor[] flavors = { DataFlavor.stringFlavor, DataFlavor.imageFlavor, DataFlavor.plainTextFlavor };

    private String plainTextFlavorValue;

    private String stringFlavorValue;

    private Image imageFlavorValue;

    UMClipObj() {
        System.out.println("UMClipObj.UMClipObj(" + ")");
    }

    UMClipObj(String stringText, String plainText) {
        System.out.println("UMClipObj.UMClipObj(" + "stringText=" + stringText + " " + "plainText=" + plainText + ")");
        this.plainTextFlavorValue = plainText;
        this.stringFlavorValue = stringText;
    }

    UMClipObj(Image imageObject) {
        System.out.println("UMClipObj.UMClipObj(" + "imageObject=" + imageObject + ")");
        this.imageFlavorValue = imageObject;
    }

    /** Returns copy of supported DataFlavors array .*/
    public DataFlavor[] getTransferDataFlavors() {
        System.out.println("UMClipObj.getTransferDataFlavors(" + ")");
        return (DataFlavor[]) flavors.clone();
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        System.out.println("UMClipObj.isDataFlavorSupported(" + "flavor=" + flavor + ")");
        for (int i = 0; i < flavors.length; i++) {
            if (flavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    /** Returns the clipboard data depending on which DataFlavor is
	requested */
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        System.out.println("UMClipObj.getTransferData(" + "flavor=" + flavor + ")");
        if (flavor.equals(DataFlavor.stringFlavor)) {
            return (Object) stringFlavorValue;
        } else if (flavor.equals(DataFlavor.plainTextFlavor)) {
            return (Object) plainTextFlavorValue;
        } else if (flavor.equals(DataFlavor.imageFlavor)) {
            return (Object) imageFlavorValue;
        } else {
            System.out.println("Exception: Attempting to access clipboard with unsupported DataFlavor");
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable transferable) {
        System.out.println("UMClipObj.lostOwnership(" + "clipboard=" + clipboard + " " + "transferable=" + transferable + ")");
        System.out.println("Lost Clipboard ownership");
    }
}
