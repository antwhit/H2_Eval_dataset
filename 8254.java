import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;

public class GameBoard extends MyPanel {

    ClientApplet ca;

    GameRoom room;

    String DELIM = "&";

    int player1, player2, r;

    public GameBoard() {
        setPreferredSize(new Dimension(400, 400));
        setBackgroundPosition(MyPanel.STRETCHED);
        setBackgroundImage("files/board.jpg");
        setLayout(new GridLayout(8, 8));
    }

    public GameBoard(ClientApplet ca, GameRoom room, int player1, int player2) {
        this.ca = ca;
        this.room = room;
        this.player1 = player1;
        this.player2 = player2;
        setPreferredSize(new Dimension(400, 400));
        setBackgroundPosition(MyPanel.STRETCHED);
        setBackgroundImage("files/board.jpg");
        setLayout(new GridLayout(8, 8));
    }

    public void initialize() {
        Random generator = new Random();
        r = generator.nextInt(1000);
        ca.send("22" + DELIM + "01" + DELIM + r);
    }

    public void processMove(String msg) {
        String[] msgParts = msg.split(DELIM);
        int msgId = Integer.parseInt(msgParts[1]);
        switch(msgId) {
            case 01:
                break;
            case 02:
                break;
        }
    }
}
