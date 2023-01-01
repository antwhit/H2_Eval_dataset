import java.awt.*;

public class CpuDebugWindow extends DebugWindow {

    public CpuDebugWindow(PIC parent, Memory mem) {
        super(parent, mem, false);
        super.CreateWindow("Special Function Register Debug", Toolkit.getDefaultToolkit().getScreenSize().width - 373, 0);
        CreateContent();
        f.setSize(370, 222);
    }

    private void CreateContent() {
        adresses.setText("             BIN        HEX  DEC  CHAR  ");
        adresses2.setText("     W\n     Z\n     C\n0x00 INDF \n0x03 STATUS \n0x04 FSR \n0x05 PORTA \n0x06 PORTB \n0x08 EEDATA \n0x09 EEADR \n0x0B INTCON\n0x85 TRISA \n0x86 TRISB ");
        content.add(adresses, BorderLayout.NORTH);
        content.add(adresses2, BorderLayout.WEST);
        content.add(textarea, BorderLayout.CENTER);
    }
}
