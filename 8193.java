import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * @author Karl Bengtsson
 */
public class ZeroFileChatWindow {

    private JFrame _chatWindowFrame;

    private JTextArea _chatTextArea = new JTextArea();

    ;

    private JScrollPane _chatTextAreaPane = new JScrollPane(_chatTextArea);

    private JPanel _bottomPanel;

    private JTextField _entryTextField;

    private JButton _sendButton;

    private JButton _sendFileButton;

    private XMPPLinkLocalChatSession _session;

    public ZeroFileChatWindow(XMPPLinkLocalChatSession sess) {
        this.ZeroFileChatWindowInit();
        _session = sess;
        _chatWindowFrame.setTitle(_session.getChatPartner().toString());
    }

    public void setWindowTitle(String t) {
        _chatWindowFrame.setTitle(t);
    }

    public void printText(String s) {
        _chatTextArea.append(s + "\n");
        _chatTextArea.setCaretPosition(_chatTextArea.getDocument().getLength());
    }

    public void sendMessage(String str) {
        printText("You:");
        printText(str + "\n");
        _session.sendMessage(str);
        _entryTextField.setText("");
    }

    private void ZeroFileChatWindowInit() {
        _chatWindowFrame = new JFrame();
        _chatWindowFrame.setLayout(new BorderLayout(1, 1));
        _chatWindowFrame.setLocation(0, 400);
        _chatWindowFrame.setSize(new Dimension(300, 200));
        _chatTextAreaPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        _chatWindowFrame.add(_chatTextAreaPane, BorderLayout.CENTER);
        _bottomPanel = new JPanel();
        _chatWindowFrame.add(_bottomPanel, BorderLayout.SOUTH);
        _bottomPanel.setLayout(new GridLayout(1, 0, 1, 1));
        _entryTextField = new JTextField();
        _sendButton = new JButton("Send");
        _sendFileButton = new JButton("Send file...");
        _bottomPanel.add(_entryTextField);
        _entryTextField.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    sendMessage(_entryTextField.getText());
                }
            }
        });
        _bottomPanel.add(_sendButton);
        _bottomPanel.add(_sendFileButton);
        _sendFileButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent sendButtonPress) {
                JFileChooser _fileChooser = new JFileChooser();
                _fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                _fileChooser.showOpenDialog(null);
                File offeredFile = _fileChooser.getSelectedFile();
                _session.offerFileTransfer(offeredFile.toString());
                printText("Offered file \"" + offeredFile.getName() + "\" to chat partner");
            }
        });
        _chatWindowFrame.setVisible(true);
        _sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent sendButtonPress) {
                sendMessage(_entryTextField.getText());
            }
        });
        _chatWindowFrame.addWindowListener(new WindowListener() {

            public void windowClosing(WindowEvent e) {
                _session.disconnect();
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }
        });
        _entryTextField.requestFocusInWindow();
    }
}
