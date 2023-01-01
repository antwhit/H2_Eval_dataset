import java.awt.*;
import java.io.IOException;

abstract class ImageGeneratorSource implements ImageSource {

    protected ImageGeneratorConfig localConfig;

    public abstract Image getImage() throws IOException;

    public ImageGeneratorSource(ImageGeneratorConfig cfg) {
        localConfig = cfg;
        System.out.println("Generator:" + localConfig.getUniqueID());
    }
}
