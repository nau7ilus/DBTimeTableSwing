package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.ContentWrapperPanel;
import de.humboldtgym.dbswing.Model.Line;
import de.humboldtgym.dbswing.Model.Trip;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class TripTableEntry extends JPanel {
    private final Trip trip;
    private final GridBagConstraints gbc = new GridBagConstraints();

    public TripTableEntry(Trip trip) {
        this.trip = trip;
        setOpaque(false);
        setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 10, 15, 10);

        addTimeAndTrain();
        addViaDest();
        addTrack();

        gbc.weightx = 0.3;
        gbc.gridx = 3;
        add(new JLabel("Column 4 (1fr)"), gbc);

        setBorder(new MatteBorder(0, 0, 1, 0, new Color(0x99AABB)));
    }

    private void addTimeAndTrain() {
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 1;

        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.SOUTH);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(115, 50));

        Line line = trip.getLine();
        JLabel trainNumberLabel = new JLabel(line.getName() + " / " + line.getId());
        trainNumberLabel.setForeground(Color.white);
        trainNumberLabel.setFont(new Font("D-DIN", Font.PLAIN, 18));
        panel.add(trainNumberLabel);

        ContentWrapperPanel timePanel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.SOUTH);
        timePanel.setOpaque(false);
        timePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel departureTimeLabel = new JLabel(trip.getFormattedWhenPlanned());
        departureTimeLabel.setForeground(Color.white);
        departureTimeLabel.setFont(new Font("D-DIN", Font.BOLD, 29));
        timePanel.add(departureTimeLabel);

        if (!trip.getFormattedWhen().equals(trip.getFormattedWhenPlanned())) {
            timePanel.add(Box.createHorizontalGlue());
            JLabel delayedTimeLabel = new JLabel(trip.getFormattedWhen());
            delayedTimeLabel.setOpaque(true);
            delayedTimeLabel.setForeground(DB_BLUE_PRIMARY_COLOR);
            delayedTimeLabel.setBackground(Color.white);
            delayedTimeLabel.setFont(new Font("D-DIN", Font.PLAIN, 20));
            timePanel.add(delayedTimeLabel);
        }

        panel.add(timePanel);
        add(panel, gbc);
    }

    private void addViaDest() {
        gbc.gridx = 1;

        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(400, 60));
        panel.setMaximumSize(new Dimension(400, 60));
        panel.setOpaque(false);

        JLabel viaLabel = new JLabel(trip.getRelevantStopoverNames());
        viaLabel.setForeground(Color.white);
        viaLabel.setFont(new Font("D-DIN", Font.PLAIN, 16));
        viaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        viaLabel.setMaximumSize(new Dimension(400, 60));

        panel.add(viaLabel);

        panel.add(Box.createVerticalStrut(5));

        JLabel destinationLabel = new JLabel(trip.getDestination().getName());
        destinationLabel.setForeground(Color.white);
        destinationLabel.setFont(new Font("D-DIN", Font.BOLD, 29));
        destinationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(destinationLabel);

        add(panel, gbc);
    }

    private void addTrack() {
        gbc.gridx = 2;
        gbc.weightx = 0.3;

        ContentWrapperPanel containerPanel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.SOUTH);
        containerPanel.setPreferredSize(new Dimension(0, 60));
        containerPanel.setMaximumSize(new Dimension(100, 60));
        containerPanel.setOpaque(false);

        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.WEST);
        panel.setOpaque(false);

        boolean isChanged = !trip.getPlatform().isEmpty() && !trip.getPlatform().equals(trip.getPlannedPlatform());
        String track = isChanged ? "<html><s>" + trip.getPlannedPlatform() + "</s></html>" : trip.getPlannedPlatform();

        JLabel trackLabel = new JLabel(track);
        trackLabel.setForeground(Color.white);
        trackLabel.setFont(new Font("D-DIN", Font.BOLD, 29));
        panel.add(trackLabel);

        if (isChanged) {
            panel.add(Box.createHorizontalStrut(10));
            JLabel changedPlatform = new JLabel(trip.getPlatform());
            changedPlatform.setOpaque(true);
            changedPlatform.setForeground(DB_BLUE_PRIMARY_COLOR);
            changedPlatform.setBackground(Color.white);
            changedPlatform.setFont(new Font("D-DIN", Font.BOLD, 29));
            panel.add(changedPlatform);
        }

        containerPanel.add(panel);

        add(containerPanel, gbc);
    }
}
