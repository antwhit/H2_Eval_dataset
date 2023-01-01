import java.awt.*;
import javax.swing.*;

public class GraphicView extends JPanel implements IView {

    CompositionModel model;

    CompositionPlot plot;

    JScrollPane scroll;

    Rule aarule;

    SequenceLabels names;

    public GraphicView(CompositionModel model) {
        assert model != null;
        this.model = model;
        this.layoutView();
        this.model.addView(this);
    }

    public void layoutView() {
        this.setLayout(new BorderLayout());
        this.plot = new CompositionPlot(model);
        this.plot.setBackground(Color.WHITE);
        this.scroll = new JScrollPane(plot);
        this.aarule = new Rule(model, Rule.HORIZONTAL);
        this.names = new SequenceLabels(model);
        names.setPreferredHeight((int) (model.numSeqs() * model.getZoomHeight()));
        scroll.setColumnHeaderView(aarule);
        scroll.setRowHeaderView(names);
        this.add(scroll, BorderLayout.CENTER);
    }

    public void updateView() {
    }
}
