package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.ContentWrapperPanel;
import de.humboldtgym.dbswing.Model.Station;
import de.humboldtgym.dbswing.Model.Trip;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class TimetableView extends JFrame {
    private final Station station;
    public JButton retryRetrieve;
    private JPanel tripsPanel;
    private final JPanel containerPanel;
    private JScrollPane scrollPane;

    public TimetableView(Station station) {
        this.station = station;

        setBounds(100, 100, 1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bahnhofstafel — " + station.getName());

        containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(DB_BLUE_PRIMARY_COLOR);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 20));
        getContentPane().add(containerPanel);

        tripsPanel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.NORTH);
        tripsPanel.setOpaque(false);

        JLabel departurePlannedTime = new JLabel("Loading...");
        departurePlannedTime.setForeground(Color.white);
        tripsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        departurePlannedTime.setFont(new Font("D-DIN", Font.BOLD, 28));
        tripsPanel.add(departurePlannedTime);

        retryRetrieve = new JButton("Retry");
        tripsPanel.add(retryRetrieve);

        scrollPane = new JScrollPane(tripsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        containerPanel.add(scrollPane);
    }

    public void updateTrips(List<Trip> newTrips) {
        tripsPanel = new ContentWrapperPanel(BoxLayout.Y_AXIS,BorderLayout.NORTH);
        tripsPanel.setOpaque(false);

        for (Trip trip : newTrips) {
            tripsPanel.add(new TripTableEntry(trip));
        }

        scrollPane.setViewportView(tripsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}
