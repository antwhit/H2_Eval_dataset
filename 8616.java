import java.io.*;

public class ImageBufferItem {

    public ImageConverter imageConverter = null;

    public int sequenceNumber = -1;

    public long timeStamp = -1;

    private int rgbDiffCount = -1;

    public ImageBufferItem(ImageConverter converter, int seqNumber, long time) {
        imageConverter = converter;
        sequenceNumber = seqNumber;
        timeStamp = time;
        rgbDiffCount = imageConverter.getWidth() * imageConverter.getHeight() * 3;
    }

    public void setRgbDiffCount(int diffCount) {
        rgbDiffCount = diffCount;
    }

    public float getRgbDiffRatio() {
        return ((float) (rgbDiffCount) * 100.0f) / ((float) (imageConverter.getWidth() * imageConverter.getHeight() * 3.0f));
    }
}
