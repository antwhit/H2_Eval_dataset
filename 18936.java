import nextapp.echo.Button;
import nextapp.echo.ContentPane;
import nextapp.echo.Filler;
import nextapp.echo.Font;
import nextapp.echo.Label;
import nextapp.echo.Color;
import nextapp.echo.event.ActionListener;
import nextapp.echo.event.ActionEvent;
import java.util.*;

class NewsScreen extends Screen implements ActionListener {

    private Label subTitleLabel = new Label("News");

    public NewsScreen(ScreenController screenController) {
        super();
        super.screenController = screenController;
        displayScreen();
    }

    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getActionCommand().equals("go writenews")) {
            screenController.displayWriteNews();
        }
    }

    public void displayScreen() {
        super.displayScreen(subTitleLabel);
        GameNewsList gameNewsList = GameNewsList.load();
        Vector newsList = gameNewsList.getGameNewsList();
        if (gameNewsList.size() == 0) {
            contentPanel.add(new Label("No news"));
            contentPanel.add(Filler.createVerticalStrut(10));
        }
        int counter = 0;
        while (counter < gameNewsList.size()) {
            displaySingleNewsItem((News) newsList.elementAt(counter));
            contentPanel.add(Filler.createVerticalStrut(10));
            counter++;
        }
        Button writeNewsScrButton = new Button("Write News");
        writeNewsScrButton.setForeground(Color.WHITE);
        writeNewsScrButton.setBackground(Color.BLACK);
        writeNewsScrButton.setActionCommand("go writenews");
        writeNewsScrButton.addActionListener(this);
        contentPanel.add(writeNewsScrButton);
    }

    public void displaySingleNewsItem(News news) {
        Font labelFont = null;
        Label textLabel = null;
        textLabel = new Label(news.getHeadline());
        labelFont = new Font(Font.VERDANA, Font.PLAIN, 14);
        textLabel.setFont(labelFont);
        contentPanel.add(textLabel);
        contentPanel.add(Filler.createVerticalStrut(1));
        textLabel = new Label("Written by " + news.getAuthor() + " on " + news.getDateAsString());
        labelFont = new Font(Font.VERDANA, Font.PLAIN, 8);
        textLabel.setFont(labelFont);
        contentPanel.add(textLabel);
        contentPanel.add(Filler.createVerticalStrut(1));
        textLabel = new Label(news.getTeaser());
        labelFont = new Font(Font.VERDANA, Font.BOLD, 10);
        textLabel.setFont(labelFont);
        contentPanel.add(textLabel);
        contentPanel.add(Filler.createVerticalStrut(3));
        textLabel = new Label(news.getText());
        contentPanel.add(textLabel);
    }
}
