import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TestFont extends Canvas {

    int Base = 10;

    int Height = 20;

    public int Show(Graphics g, int X) {
        Font CurFont;
        FontMetrics Metrics;
        int Line;
        int Width;
        int MaxWidth;
        CurFont = g.getFont();
        Metrics = g.getFontMetrics();
        Line = 2;
        g.drawString(CurFont.getFamily(), X, Base + Line++ * Height);
        g.drawString(CurFont.getName(), X, Base + Line++ * Height);
        g.drawString(CurFont.getFontName(), X, Base + Line++ * Height);
        g.drawString(CurFont.getSize() + " ", X, Base + Line++ * Height);
        g.drawString(CurFont.getStyle() + " ", X, Base + Line++ * Height);
        ++Line;
        g.drawString(Metrics.getAscent() + " ", X, Base + Line++ * Height);
        g.drawString(Metrics.getDescent() + " ", X, Base + Line++ * Height);
        g.drawString(Metrics.getLeading() + " ", X, Base + Line++ * Height);
        g.drawString(Metrics.getHeight() + " ", X, Base + Line++ * Height);
        g.drawString(Metrics.getMaxAdvance() + " ", X, Base + Line++ * Height);
        g.drawString("ABCDEFGHIJ", X, Base + Line++ * Height);
        g.drawString(Metrics.stringWidth("A") + " ", X, Base + Line++ * Height);
        g.drawString("abcdefghij", X, Base + Line++ * Height);
        g.drawString(Metrics.stringWidth("abcdefghij") + " ", X, Base + Line++ * Height);
        g.drawString("0123456789", X, Base + Line++ * Height);
        g.drawString(Metrics.stringWidth("0123456789") + " ", X, Base + Line++ * Height);
        MaxWidth = Metrics.stringWidth(CurFont.getFontName());
        Width = Metrics.stringWidth("ABCDEFGHIJ");
        if (Width > MaxWidth) MaxWidth = Width;
        X = X + MaxWidth + 5;
        return X;
    }

    public void paint(Graphics g) {
        int X;
        int Line;
        Font CurFont = g.getFont();
        Font Serif = new Font("Serif", Font.ITALIC, 12);
        Font Courier = new Font("Courier", Font.BOLD | Font.ITALIC, 12);
        Font CourierNew = new Font("Courier New", Font.PLAIN, 12);
        Font SansSerif = new Font("Helvetica", Font.BOLD, 12);
        Font Roman = new Font("Roman", Font.BOLD | Font.ITALIC, 12);
        Font NewRoman = new Font("Times New Roman", Font.PLAIN, 12);
        FontMetrics Metrics = g.getFontMetrics();
        Line = 1;
        g.drawString("Font", 10, Base + Line++ * Height);
        g.drawString("Family", 10, Base + Line++ * Height);
        g.drawString("Name", 10, Base + Line++ * Height);
        g.drawString("FontName", 10, Base + Line++ * Height);
        g.drawString("Size", 10, Base + Line++ * Height);
        g.drawString("Style", 10, Base + Line++ * Height);
        g.drawString("FontMetrics", 10, Base + Line++ * Height);
        g.drawString("Ascent", 10, Base + Line++ * Height);
        g.drawString("Descent", 10, Base + Line++ * Height);
        g.drawString("Leading", 10, Base + Line++ * Height);
        g.drawString("Height", 10, Base + Line++ * Height);
        g.drawString("MaxAdvance", 10, Base + Line++ * Height);
        g.drawString("Uppercase", 10, Base + Line++ * Height);
        g.drawString("Width", 10, Base + Line++ * Height);
        g.drawString("Lowercase", 10, Base + Line++ * Height);
        g.drawString("Width", 10, Base + Line++ * Height);
        g.drawString("Digits", 10, Base + Line++ * Height);
        g.drawString("Width", 10, Base + Line++ * Height);
        X = Metrics.stringWidth("MaxAdvance") + 20;
        X = Show(g, X);
        g.setFont(SansSerif);
        X = Show(g, X);
        g.setFont(CourierNew);
        X = Show(g, X);
        g.setFont(Serif);
        X = Show(g, X);
        g.setFont(Roman);
        X = Show(g, X);
        g.setFont(NewRoman);
        X = Show(g, X);
        g.setFont(Courier);
        X = Show(g, X);
    }
}
