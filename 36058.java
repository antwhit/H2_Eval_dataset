import javax.swing.JApplet;
import java.lang.String;
import java.awt.BorderLayout;

/**
 * Main Class; container for the
 * different subclasses of jpanel. 
 * -one toolbar
 * -one textedit area
 * -different footers for user interaction (ex to insert the link datas)
 *
 * the class provides a mechanism to load different footers
 * (take a look at the method boolean loadNewFooter(CTxtAppletFooter footer)
 * and loadStandartFooter()
 *
 * @author Moritz Zumb�hl
 */
public class CTxtApplet extends JApplet {

    CTxtAppletToolBar m_ToolBar;

    CTxtAppletTextEdit m_TextEdit = new CTxtAppletTextEdit();

    CTxtAppletStandartFooter m_Footer = new CTxtAppletStandartFooter();

    /** Standart constructor */
    public CTxtApplet() {
    }

    /** call by the browser. runs at the start of the applet */
    public void init() {
        m_ToolBar = new CTxtAppletToolBar(this.getCodeBase(), m_TextEdit);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(m_ToolBar, BorderLayout.NORTH);
        this.getContentPane().add(m_TextEdit, BorderLayout.CENTER);
        this.getContentPane().add(m_Footer, BorderLayout.SOUTH);
        m_ToolBar.init();
        m_TextEdit.init(m_ToolBar);
        m_Footer.init();
    }

    /** call by the browser to get informations about the applet */
    public String getAppletInfo() {
        return new String("txtApplet; take a look at txtapplet.sourceforge.net");
    }
}
