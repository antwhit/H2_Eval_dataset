import javax.swing.JSlider;
import javax.swing.event.*;
import java.util.*;

class SliderListener implements ChangeListener {

    public SliderListener() {
    }

    public void stateChanged(ChangeEvent e) {
        int sliderValue = ((JSlider) e.getSource()).getValue();
        PlayArea.cardHeight = sliderValue * PlayArea.baseCardHeight / 50;
        PlayArea.cardWidth = (int) (PlayArea.cardHeight * (2.5 / 3.5));
        ListIterator<PlayableCard> iterator = PlayArea.displayedCards.listIterator();
        while (iterator.hasNext()) {
            PlayableCard element = iterator.next();
            element.setImage(null);
            element.updateAttachmentLocations();
            ArrayList<PlayableCard> attachments = element.getAllAttachments();
            for (int i = 0; i < attachments.size(); i++) {
                attachments.get(i).setImage(null);
            }
        }
        Main.playArea.repaint();
    }
}
