import javax.swing.*;

public class JLabelStatus extends JLabel {

    String fullMessage;

    public void setTextO(String text) {
        fullMessage = text;
        super.setText(text);
    }

    public String getTextO() {
        return fullMessage;
    }

    public void refreshText(int x) {
        super.setText(this.fullMessage);
        if ((fullMessage.length() * 6.6) > (x - 20)) {
            for (int y = fullMessage.length(); y > 0; y--) {
                String temp = this.fullMessage.substring(0, y) + "...";
                if ((temp.length() * 6.6) < (x - 15)) {
                    this.setText(temp);
                    y = 0;
                }
            }
        }
    }
}
