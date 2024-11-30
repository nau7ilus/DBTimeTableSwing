package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.Model.Station;

import javax.swing.*;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class TimetableView extends JFrame {
    private final Station station;

    public TimetableView(Station station) {
        this.station = station;

        setBounds(100, 100, 1500, 844);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bahnhofstafel — " + station.getName());

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(DB_BLUE_PRIMARY_COLOR);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 20));
        getContentPane().add(containerPanel);
    }
}
