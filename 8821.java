import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClientGui extends JFrame implements MouseListener, KeyListener, WindowListener, ClientUiInterface {

    private Message msg;

    private JTextArea outputT;

    private JTextField inputT;

    private Client client;

    /**
     * Constructor for objects of class ClientGui
     */
    public ClientGui(String title, Client myClient) {
        super(title);
        client = myClient;
        this.addWindowListener(this);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        outputT = new JTextArea(40, 35);
        outputT.setLineWrap(true);
        outputT.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(outputT);
        contentPane.add("Center", scrollPane);
        outputT.setEditable(false);
        inputT = new JTextField();
        inputT.addKeyListener(this);
        contentPane.add("South", inputT);
        pack();
        setVisible(true);
        inputT.requestFocus();
    }

    public void displayMsg(Message msg) {
        String line = msg.getContent();
        outputT.append(line + "\n");
    }

    public void disconnect() {
        inputT.setVisible(false);
        validate();
        outputT.append("**Connection to the Server Lost**\n");
    }

    public void mouseClicked(MouseEvent e) {
    }

    ;

    /**
  * KeyPressed Event occurs when a key is pressed on the users keyboard.
  * 
  * @param  pushedKey    the KeyEvent object assosiated with the event.
  **/
    public void keyPressed(KeyEvent pushedKey) {
        int keyCode = pushedKey.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            if (inputT.getText() != "") {
                Message sndMsg = new Message();
                if (inputT.getText().equals("quit")) {
                    client.run = false;
                    sndMsg.setQuit(true);
                    client.outputMsg(sndMsg);
                    dispose();
                } else {
                    if (inputT.getText().equals("quitserver")) {
                        sndMsg.setServerQuit(true);
                        client.outputMsg(sndMsg);
                    } else {
                        sndMsg.setContent(inputT.getText());
                        client.outputMsg(sndMsg);
                        inputT.setText("");
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    ;

    public void keyTyped(KeyEvent e) {
    }

    ;

    public void mouseEntered(MouseEvent e) {
    }

    ;

    public void mouseExited(MouseEvent e) {
    }

    ;

    public void mousePressed(MouseEvent e) {
    }

    ;

    public void mouseReleased(MouseEvent e) {
    }

    ;

    public void windowActivated(WindowEvent e) {
    }

    ;

    public void windowClosed(WindowEvent e) {
    }

    ;

    /**
  * windowClosing Event occurs when the window is closed by the user or program
  * 
  * @param      e    the WindowEvent object assosiated with the event.
  **/
    public void windowClosing(WindowEvent e) {
        if (client.uiListener != null) {
            client.run = false;
            Message sndMsg = new Message();
            sndMsg.setQuit(true);
            client.outputMsg(sndMsg);
            dispose();
        } else {
            dispose();
        }
    }

    public void windowDeactivated(WindowEvent e) {
    }

    ;

    public void windowDeiconified(WindowEvent e) {
    }

    ;

    public void windowIconified(WindowEvent e) {
    }

    ;

    public void windowOpened(WindowEvent e) {
    }

    ;
}
