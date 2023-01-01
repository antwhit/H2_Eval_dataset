import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.*;
import javax.media.jai.*;
import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.imageio.stream.*;
import org.w3c.dom.*;

/**
 * Print out all stream metadata associated with the image stream for a given image file
 * as well as all image metadata for an image at a particular image in the stream.
 */
public class MetadataReadExample extends JFrame {

    public static void main(String[] args) throws Throwable {
        if (args.length < 0) {
            throw new IllegalArgumentException("Usage: java MetadataReadExample filename [firstIndex [lastIndex]]");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        } else if (!file.isFile()) {
            throw new IllegalArgumentException(file + " is not a file");
        } else if (!file.canRead()) {
            throw new IllegalArgumentException(file + " is not readable");
        }
        int firstIndex = 0;
        int lastIndex = firstIndex;
        if (args.length > 1) {
            firstIndex = Integer.valueOf(args[1]).intValue();
            if (firstIndex < 0) {
                throw new IllegalArgumentException("firstIndex < 0");
            }
            if (args.length > 2) {
                lastIndex = Integer.valueOf(args[2]).intValue();
                if (lastIndex < firstIndex) {
                    throw new IllegalArgumentException("lastIndex < firstIndex");
                }
            }
        }
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator readers = ImageIO.getImageReaders(input);
        if (readers == null || !readers.hasNext()) {
            throw new RuntimeException("No ImageReaders found");
        }
        ImageReader reader = null;
        while (readers.hasNext() && (reader == null || !reader.getClass().getName().startsWith("com.sun.media"))) {
            reader = (ImageReader) readers.next();
        }
        if (reader == null) {
            throw new RuntimeException("No com.sun.media ImageReader found");
        }
        System.out.println("Using ImageReader " + reader.getClass().getName());
        reader.setInput(input);
        IIOMetadata streamMetadata = reader.getStreamMetadata();
        if (streamMetadata != null) {
            String[] metadataFormatNames = streamMetadata.getMetadataFormatNames();
            if (metadataFormatNames != null) {
                for (int j = 0; j < metadataFormatNames.length; j++) {
                    System.out.println("\n--- Stream metadata --- " + metadataFormatNames[j] + "\n");
                    IIOExampleUtils.printMetadata(streamMetadata, metadataFormatNames[j]);
                }
            }
        }
        for (int imageIndex = firstIndex; imageIndex <= lastIndex; imageIndex++) {
            IIOMetadata imageMetadata = reader.getImageMetadata(imageIndex);
            if (imageMetadata != null) {
                String[] metadataFormatNames = imageMetadata.getMetadataFormatNames();
                if (metadataFormatNames != null) {
                    for (int j = 0; j < metadataFormatNames.length; j++) {
                        System.out.println("\n--- Image metadata --- " + metadataFormatNames[j] + "\n");
                        IIOExampleUtils.printMetadata(imageMetadata, metadataFormatNames[j]);
                    }
                }
            }
        }
    }
}
