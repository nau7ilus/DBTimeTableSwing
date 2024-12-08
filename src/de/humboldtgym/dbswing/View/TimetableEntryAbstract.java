package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.ContentWrapperPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public abstract class TimetableEntryAbstract extends JPanel {
    protected final GridBagConstraints gbc = new GridBagConstraints();
    private Color borderColor = new Color(0x99AABB);

    public TimetableEntryAbstract() {
        setOpaque(false);
        setLayout(new GridBagLayout());
    }

    protected static JPanel getTimeAndTrainContainer() {
        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.SOUTH);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(118, 70));
        return panel;
    }

    protected static Dimension getViaDestDimension() {
        return new Dimension(400, 70);
    }

    protected static JPanel getViaDestContainer() {
        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.SOUTH);
        Dimension panelDimension = getViaDestDimension();
        panel.setPreferredSize(panelDimension);
        panel.setMaximumSize(panelDimension);
        panel.setOpaque(false);
        return panel;
    }

    protected static JPanel getTrackContainer(int align) {
        ContentWrapperPanel panel = new ContentWrapperPanel(align, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setMaximumSize(new Dimension(100, 60));
        panel.setOpaque(false);
        return panel;
    }

    protected static JPanel getRemarksContainer() {
        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(150, 60));
        panel.setOpaque(false);
        return panel;
    }

    protected void init() {
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 15, 10);

        gbc.gridx = 0;
        add(addBottomBorder(addTimeAndTrain()), gbc);

        gbc.gridx = 1;
        add(addBottomBorder(addViaDest()), gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.3;
        add(addBottomBorder(addTrack()), gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 3;
        add(addBottomBorder(addRemarks()), gbc);
    }

    protected void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    protected JPanel addBottomBorder(JPanel panel) {
        panel.add(Box.createVerticalStrut(10));
        panel.setBorder(new MatteBorder(0, 0, 1, 0, borderColor));
        return panel;
    }

    protected abstract JPanel addTimeAndTrain();

    protected abstract JPanel addViaDest();

    protected abstract JPanel addTrack();

    protected abstract JPanel addRemarks();
}
