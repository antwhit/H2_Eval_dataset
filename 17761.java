import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

class CurrentTracksUI extends JPanel implements IView {

    CoreModel model;

    JScrollPane scrollTracks;

    JList tracks;

    JButton removeTracks;

    public CurrentTracksUI(CoreModel m) {
        super();
        assert m != null;
        this.model = m;
        createWidgets();
        layoutView();
        registerControllers();
        this.model.addView(this);
    }

    private void createWidgets() {
        tracks = new JList(this.model.getTracks().toArray());
        tracks.setLayoutOrientation(JList.VERTICAL);
        scrollTracks = new JScrollPane(tracks);
        scrollTracks.setMinimumSize(new Dimension(0, 100));
        removeTracks = new JButton("Remove");
    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(scrollTracks);
        this.add(removeTracks);
    }

    public void updateView() {
        this.tracks.setListData(this.model.getTracks().toArray(new BaseTrack[0]));
    }

    private void registerControllers() {
        this.removeTracks.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Object[] selectedTracks = CurrentTracksUI.this.tracks.getSelectedValues();
                for (Object selectedTrack : selectedTracks) {
                    CurrentTracksUI.this.model.removeTrack(selectedTrack);
                }
            }
        });
    }
}
