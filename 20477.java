import java.lang.System;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import edu.berkeley.guir.util.*;

/**
 * Controls for setting how gestures are displayed
 
 * <P>
 * This software is distributed under the 
 * <A HREF="http://guir.cs.berkeley.edu/projects/COPYRIGHT.txt">
 * Berkeley Software License</A>.
 */
public class VFrame extends JFrame {

    public VFrame() {
        super("Coloring Parameters");
        buildUI();
    }

    protected void buildUI() {
        Container contents = getContentPane();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        contents.setLayout(gridBag);
        JLabel label = new JLabel("Set Thresholds (in degrees)");
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gridBag.setConstraints(label, constraints);
        contents.add(label);
        label = new JLabel("Lower");
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(label, constraints);
        contents.add(label);
        final JTextField lower = new JTextField((VGesturePointsDisplay.straightThreshold / Math.PI * 180) + "");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(lower, constraints);
        contents.add(lower);
        label = new JLabel("Upper");
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.WEST;
        gridBag.setConstraints(label, constraints);
        contents.add(label);
        final JTextField upper = new JTextField((VGesturePointsDisplay.cornerThreshold / Math.PI * 180) + "");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        gridBag.setConstraints(upper, constraints);
        contents.add(upper);
        JButton applyButton = new JButton("Apply");
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gridBag.setConstraints(applyButton, constraints);
        contents.add(applyButton);
        applyButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                Double d;
                String s = lower.getText();
                try {
                    d = Double.valueOf(s);
                    VGesturePointsDisplay.straightThreshold = d.doubleValue() / 180 * Math.PI;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(VFrame.this, "Invalid number '" + s + "'.", "Bad Number", JOptionPane.ERROR_MESSAGE);
                }
                s = upper.getText();
                try {
                    d = Double.valueOf(s);
                    VGesturePointsDisplay.cornerThreshold = d.doubleValue() / 180 * Math.PI;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(VFrame.this, "Invalid number '" + s + "'.", "Bad Number", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pack();
    }
}
