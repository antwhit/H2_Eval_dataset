import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Die OZIDPicturePanel-Klasse wird zur Darstellung des aktuelle ausgew�hlten
 * Bildes und dem Vergleich mit dem Referenzbild verwendet.
 * 
 * @author $Author: $
 * @version $Revision: $, $Date: $ UTC
 */
@SuppressWarnings("serial")
public class OZIDPicturePanel extends JPanel {

    private OZIDDataModel modell;

    private ResizingImageLabel bildOriginal;

    private ResizingImageLabel bildFarbdifferenz;

    private ResizingImageLabel bildReferenz;

    private ResizingImageLabel bildResultat;

    /**
    * 
    * Konstruktor des OZID-Picture-Panels. �bernimmt die Konfiguration.
    * Initialisiert die Komponenten.
    * 
    * @param model
    *           Konfiguration
    */
    public OZIDPicturePanel(OZIDDataModel model) {
        if (model == null) {
            throw new NullPointerException("conf is null");
        }
        modell = model;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 2, 10, 10));
        bildOriginal = modell.getOriginalPictureColoredLabel();
        JLabel bildOriginalLabel = new JLabel("Aufgenommenes Bild");
        JPanel bildOriginalPanel = new JPanel();
        bildOriginalPanel.setLayout(new BorderLayout());
        bildOriginalPanel.add(bildOriginalLabel, BorderLayout.NORTH);
        bildOriginalPanel.add(bildOriginal, BorderLayout.CENTER);
        bildReferenz = modell.getReferenzPictureLabel();
        JLabel bildReferenzLabel = new JLabel("Referenzbild");
        JPanel bildReferenzPanel = new JPanel();
        bildReferenzPanel.setLayout(new BorderLayout());
        bildReferenzPanel.add(bildReferenzLabel, BorderLayout.NORTH);
        bildReferenzPanel.add(bildReferenz, BorderLayout.CENTER);
        bildFarbdifferenz = modell.getDifferencePictureLabel();
        JLabel bildGraustufenLabel = new JLabel("Farbdifferenz im �berwachten Bereich");
        JPanel bildGraustufenPanel = new JPanel();
        bildGraustufenPanel.setLayout(new BorderLayout());
        bildGraustufenPanel.add(bildGraustufenLabel, BorderLayout.NORTH);
        bildGraustufenPanel.add(bildFarbdifferenz, BorderLayout.CENTER);
        bildResultat = modell.getComparedPictureLabel();
        JLabel bildResultatLabel = new JLabel("Erkanntes Eindringen");
        JPanel bildResultatPanel = new JPanel();
        bildResultatPanel.setLayout(new BorderLayout());
        bildResultatPanel.add(bildResultatLabel, BorderLayout.NORTH);
        bildResultatPanel.add(bildResultat, BorderLayout.CENTER);
        add(bildOriginalPanel);
        add(bildReferenzPanel);
        add(bildGraustufenPanel);
        add(bildResultatPanel);
        PicturePopupMouselistener mouseListener = new PicturePopupMouselistener();
        bildReferenz.addMouseListener(mouseListener);
        bildResultat.addMouseListener(mouseListener);
        bildOriginal.addMouseListener(mouseListener);
        bildFarbdifferenz.addMouseListener(mouseListener);
    }

    /**
    * 
    * Getter f�r das Datenmodell.
    * 
    * @return Datenmodell
    */
    public OZIDDataModel getModel() {
        return modell;
    }
}
