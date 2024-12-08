package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.ContentWrapperPanel;
import de.humboldtgym.dbswing.Model.Station;
import de.humboldtgym.dbswing.Model.Trip;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class TimetableView extends JFrame {
    private final JScrollPane scrollPane;
    public JButton retryRetrieve;
    private JPanel tripsPanel;

    public TimetableView(Station station) {
        setBounds(100, 100, 1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bahnhofstafel — " + station.getName());

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.setBackground(DB_BLUE_PRIMARY_COLOR);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 20));
        getContentPane().add(containerPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        TimetableWindowHeader windowHeader = new TimetableWindowHeader();
        headerPanel.add(windowHeader);

        TableHeader tableHeader = new TableHeader();
        headerPanel.add(tableHeader);

        containerPanel.add(headerPanel, BorderLayout.NORTH);

        tripsPanel = new JPanel(new BorderLayout());
        tripsPanel.setOpaque(false);
        tripsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        retryRetrieve = new JButton("Retry");
        tripsPanel.add(retryRetrieve);

        scrollPane = new JScrollPane(tripsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        containerPanel.add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTrips(List<Trip> newTrips) {
        tripsPanel = new ContentWrapperPanel(BoxLayout.Y_AXIS, BorderLayout.NORTH);
        tripsPanel.setOpaque(false);

        for (Trip trip : newTrips) {
            tripsPanel.add(new TripTableEntry(trip));
        }

        scrollPane.setViewportView(tripsPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}
