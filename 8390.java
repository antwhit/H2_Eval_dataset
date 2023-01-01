import java.awt.*;
import javax.swing.*;

/** Renderizador de itens de listas com margens horizontais. */
public class ComboCellRenderer extends DefaultListCellRenderer {

    /** Construtor da classe extendida. */
    public ComboCellRenderer() {
        super();
    }

    /**
   * Método objeto da extensão incrementando a área de renderização para
   * que haja margens horizontais entre o conteúdo e as bordas.
   *
   * @param insets O insets do super componente.
   * @return Insets do super componente incrementado de margens.
  */
    @Override
    public Insets getInsets(Insets insets) {
        Insets i = super.getInsets(insets);
        FontMetrics metric = getFontMetrics(UIManager.getFont("Panel.font"));
        int margin = (2 * metric.charWidth('M')) / 5;
        i.left += margin;
        i.right += margin;
        return i;
    }

    static final long serialVersionUID = -8714038156164748665L;
}
