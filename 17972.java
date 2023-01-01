import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Cseki
 */
public class PlainRabbitView extends RabbitView {

    public PlainRabbitView(Rabbit ra) {
        r = ra;
        r.setView(this);
    }

    public void createView() {
    }

    public void notifyView() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("rabbit_car_img_small.png"));
        } catch (IOException e) {
            System.out.println("img not found");
        }
        gr.drawImage(img, r.getactualRoadElement().getView().getX() * 30, r.getactualRoadElement().getView().getY() * 30, null);
    }
}
