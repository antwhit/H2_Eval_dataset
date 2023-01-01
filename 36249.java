import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.NumberFormat;

public class ConversionPanel extends JPanel {

    DecimalField textField;

    JComboBox unitChooser;

    JSlider slider;

    ConverterRangeModel sliderModel;

    Converter controller;

    Unit[] units;

    String title;

    static final boolean DEBUG = false;

    static final boolean COLORS = false;

    static final int MAX = 10000;

    ConversionPanel(Converter myController, String myTitle, Unit[] myUnits, ConverterRangeModel myModel) {
        if (COLORS) {
            setBackground(Color.cyan);
        }
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(myTitle), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        controller = myController;
        units = myUnits;
        title = myTitle;
        sliderModel = myModel;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        textField = new DecimalField(0, 10, numberFormat);
        textField.setValue(sliderModel.getDoubleValue());
        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sliderModel.setDoubleValue(textField.getValue());
            }
        });
        unitChooser = new JComboBox();
        for (int i = 0; i < units.length; i++) {
            unitChooser.addItem(units[i].description);
        }
        unitChooser.setSelectedIndex(0);
        sliderModel.setMultiplier(units[0].multiplier);
        unitChooser.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int i = unitChooser.getSelectedIndex();
                sliderModel.setMultiplier(units[i].multiplier);
                controller.resetMaxValues(false);
            }
        });
        slider = new JSlider(sliderModel);
        sliderModel.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                textField.setValue(sliderModel.getDoubleValue());
            }
        });
        JPanel unitGroup = new JPanel() {

            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            public Dimension getPreferredSize() {
                return new Dimension(150, super.getPreferredSize().height);
            }

            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        if (COLORS) {
            unitGroup.setBackground(Color.blue);
        }
        unitGroup.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        unitGroup.setLayout(new BoxLayout(unitGroup, BoxLayout.Y_AXIS));
        unitGroup.add(textField);
        unitGroup.add(slider);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(unitGroup);
        add(unitChooser);
        unitGroup.setAlignmentY(TOP_ALIGNMENT);
        unitChooser.setAlignmentY(TOP_ALIGNMENT);
    }

    /** 
     * Returns the multiplier (units/meter) for the currently
     * selected unit of measurement.
     */
    public double getMultiplier() {
        return sliderModel.getMultiplier();
    }

    public double getValue() {
        return sliderModel.getDoubleValue();
    }
}
