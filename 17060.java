import java.awt.*;

/**
 * Borda especializada de componente com renderização de logotipo opcional
 * e transparência alfa.
*/
public class LogoBorder extends TransparentBorder {

    /**
   * Construtor da classe de borda especializada para componente com
   * renderização de logotipo opcional no lado esquerdo de cima para
   * baixo e opcionalmente usando transparência alfa.
   * O logotipo pode ser transparente, combinando com a cor de fundo
   * especifica para sua subárea e se não for fornecida, usará a cor
   * de fundo default do menu no Look&Feel efetivo.  A transparência
   * depende da opacidade declarada do componente que fará seu uso.
   *
   * @param logo Imagem do logotipo com transparência opcional.
   * @param logoBgColor Cor de fundo da área do canvas do logotipo.
   * @param transparent Indicador de uso de transparência alfa.
  */
    public LogoBorder(Image logo, Color logoBgColor, boolean transparent) {
        super();
        if ((this.logo = logo) != null) {
            logoWidth = logo.getWidth(null);
            insets.left = 1 + logoWidth + 2;
            this.logoBgColor = logoBgColor;
        }
        if (transparent && (this.logoBgColor != null)) {
            this.logoBgColor = getTransparentColor(logoBgColor);
        }
    }

    /**
   * Renderiza a borda do componente na posição e dimensões do canvas.
   *
   * Observação: A renderização ocorrerá sempre que o componente se
   *             tornar visível.
   *
   * @param c Componente que usará a borda.
   * @param g Canvas de renderização.
   * @param x Ordenada do canto superior esquerdo do canvas.
   * @param y Abicissa do canto superior esquerdo do canvas.
   * @param w Largura do canvas em pixels.
   * @param h Altura do canvas em pixels.
  */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        super.paintBorder(c, g, x, y, w, h);
        if (logo != null) {
            int x1 = x + 1, y1 = y + 1, xi = x + insets.left, xa, ya;
            g.setColor(getTransparentColor(borderColor));
            g.drawLine(xa = (xi - 2), y1, xa, ya = (y + h - 2));
            g.setColor(reflexColor);
            g.drawLine(xa = (xi - 1), y1, xa, ya);
            if (logoBgColor != null) {
                g.setColor(logoBgColor);
                g.fillRect(x1, y1, logoWidth, h - 2);
            }
            g.drawImage(logo, x1, y + 4, c);
            g.drawLine(x1, y1, xi - 3, y1);
            g.drawLine(x1, y1, x1, ya);
        }
    }

    /** Logotipo renderizado no canto superior esquerdo do componente. */
    private Image logo;

    /** Cor de fundo opcional do logotipo. */
    private Color logoBgColor;

    /** Largura da imagem do logotipo. */
    private int logoWidth;

    private static final long serialVersionUID = 7731974740588356498L;
}
