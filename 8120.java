import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

class GameScreen {

    Container mainContainer = DurakApplet.mainContainer;

    Client client;

    public GameScreen(Client client) {
        this.mainContainer = mainContainer;
        this.client = client;
        setUpScreen();
    }

    static final int top_x_size = Durak.x_size / 3;

    static final int top_y_size = 5 * Durak.y_size / 16;

    static final int center_x_size = Durak.x_size / 4;

    static final int center_y_size = (int) ((float) (5 * Durak.y_size) / 16);

    static final int table_x_size = Durak.x_size - 2 * center_x_size;

    static final int player_x_size = 2 * Durak.x_size / 5;

    static final int bottom_x_size = (Durak.x_size - player_x_size) / 2;

    static final int bottom_y_size = Durak.y_size - top_y_size - center_y_size;

    public static OpponentPanel op3Panel = new OpponentPanel(top_x_size, top_y_size);

    public static OpponentPanel op1Panel = new OpponentPanel(center_x_size, center_y_size);

    public static OpponentPanel op5Panel = new OpponentPanel(center_x_size, center_y_size);

    public static OpponentPanel op2Panel = new OpponentPanel(top_x_size, top_y_size);

    public static OpponentPanel op4Panel = new OpponentPanel(top_x_size, top_y_size);

    public static TablePanel tablePanel = new TablePanel(table_x_size, center_y_size);

    public static PlayerPanel playerPanel = new PlayerPanel(player_x_size, bottom_y_size);

    public static DeckPanel deckPanel = new DeckPanel(bottom_x_size, bottom_y_size);

    public void setUpScreen() {
        JPanel chatContainer = new JPanel();
        chatContainer.setBackground(Color.white);
        JScrollPane charAreaScrollPane = new JScrollPane(PreGameScreen.chatArea);
        charAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel typeFieldPanel = new JPanel();
        typeFieldPanel.setBackground(Color.white);
        typeFieldPanel.setLayout(new BorderLayout());
        typeFieldPanel.add(PreGameScreen.typeField, BorderLayout.PAGE_START);
        GridBagLayout chatLayout = new GridBagLayout();
        chatContainer.setLayout(chatLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        chatLayout.setConstraints(charAreaScrollPane, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.5;
        chatLayout.setConstraints(typeFieldPanel, c);
        chatContainer.add(charAreaScrollPane);
        chatContainer.add(typeFieldPanel);
        mainContainer.removeAll();
        mainContainer.setLayout(null);
        op2Panel.setBounds(0, 0, top_x_size, top_y_size);
        op3Panel.setBounds(top_x_size, 0, top_x_size, top_y_size);
        op4Panel.setBounds(2 * top_x_size, 0, top_x_size, top_y_size);
        op1Panel.setBounds(0, top_y_size, center_x_size, center_y_size);
        tablePanel.setBounds(center_x_size, top_y_size, table_x_size, center_y_size);
        op5Panel.setBounds(table_x_size + center_x_size, top_y_size, center_x_size, center_y_size);
        chatContainer.setBounds(0, top_y_size + center_y_size, bottom_x_size, bottom_y_size);
        playerPanel.setBounds(bottom_x_size, top_y_size + center_y_size, player_x_size, bottom_y_size);
        deckPanel.setBounds(bottom_x_size + player_x_size, top_y_size + center_y_size, bottom_x_size, bottom_y_size);
        mainContainer.add(op4Panel);
        mainContainer.add(op1Panel);
        mainContainer.add(op5Panel);
        mainContainer.add(op2Panel);
        mainContainer.add(tablePanel);
        mainContainer.add(op3Panel);
        mainContainer.add(chatContainer);
        mainContainer.add(playerPanel);
        mainContainer.add(deckPanel);
        mainContainer.doLayout();
        op4Panel.repaint();
        op1Panel.repaint();
        op5Panel.repaint();
        op2Panel.repaint();
        tablePanel.repaint();
        op3Panel.repaint();
        playerPanel.repaint();
        deckPanel.repaint();
        chatContainer.doLayout();
        charAreaScrollPane.doLayout();
        typeFieldPanel.doLayout();
        client.requestForMyCards();
        client.requestForOppCards();
        client.requestForDeckCards();
    }
}
