package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.ContentWrapperPanel;
import de.humboldtgym.dbswing.MarqueeLabel;
import de.humboldtgym.dbswing.Model.Line;
import de.humboldtgym.dbswing.Model.Trip;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class TripTableEntry extends TimetableEntryAbstract {
    private final Trip trip;

    public TripTableEntry(Trip trip) {
        super();
        this.trip = trip;
        this.init();

        this.setOpaque(true);
        this.setBackground(this.getBackgroundColor());
    }

    private Color getBackgroundColor() {
        return trip.isCancelled() ? Color.white : DB_BLUE_PRIMARY_COLOR;
    }

    private Color getForegroundColor() {
        return trip.isCancelled() ? DB_BLUE_PRIMARY_COLOR : Color.white;
    }

    @Override
    protected JPanel addTimeAndTrain() {
        JPanel panel = getTimeAndTrainContainer();

        Line line = trip.getLine();
        JLabel trainNumberLabel = new JLabel(line.getName() + " / " + line.getId());
        trainNumberLabel.setForeground(this.getForegroundColor());
        trainNumberLabel.setFont(new Font("D-DIN", Font.PLAIN, 18));
        panel.add(trainNumberLabel);

        ContentWrapperPanel timePanel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.SOUTH);
        timePanel.setOpaque(false);
        timePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel departureTimeLabel = new JLabel(trip.getFormattedWhenPlanned());
        departureTimeLabel.setForeground(this.getForegroundColor());
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
        return panel;
    }

    @Override
    protected JPanel addViaDest() {
        JPanel panel = getViaDestContainer();

        JLabel viaLabel = new JLabel(trip.getRelevantStopoverNames());
        viaLabel.setForeground(this.getForegroundColor());
        viaLabel.setFont(new Font("D-DIN", Font.PLAIN, 16));
        viaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        viaLabel.setMaximumSize(getViaDestDimension());

        panel.add(viaLabel);

        panel.add(Box.createVerticalStrut(5));

        JLabel destinationLabel = new JLabel(trip.getDestination().getName());
        destinationLabel.setForeground(this.getForegroundColor());
        destinationLabel.setFont(new Font("D-DIN", Font.BOLD, 29));
        destinationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(destinationLabel);

        return panel;
    }

    @Override
    protected JPanel addTrack() {
        JPanel containerPanel = getTrackContainer(BoxLayout.X_AXIS);

        ContentWrapperPanel panel = new ContentWrapperPanel(BoxLayout.X_AXIS, BorderLayout.WEST);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 10, 0));

        boolean isChanged = !trip.getPlatform().isEmpty() && !trip.getPlatform().equals(trip.getPlannedPlatform());
        String track = isChanged ? "<html><s>" + trip.getPlannedPlatform() + "</s></html>" : trip.getPlannedPlatform();

        JLabel trackLabel = new JLabel(track);
        trackLabel.setForeground(this.getForegroundColor());
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
        return containerPanel;
    }

    @Override
    protected JPanel addRemarks() {
        JPanel panel = getRemarksContainer();

        List<String> remarks = trip.getRemarks();
        if (!remarks.isEmpty()) {
            String joinedRemarks = String.join(" +++ ", remarks);
            MarqueeLabel marqueeLabel = new MarqueeLabel(joinedRemarks, 2);
            marqueeLabel.setBackground(Color.white);
            marqueeLabel.setFont(new Font("D-DIN", Font.PLAIN, 27));
            marqueeLabel.setForeground(DB_BLUE_PRIMARY_COLOR);
            panel.add(marqueeLabel);
        }

        return panel;
    }
}
