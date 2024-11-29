package de.humboldtgym.dbswing.View;

import javax.swing.*;
import java.awt.*;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class StationSearchFrame extends JFrame {
    public StationSearchFrame() {
        setBounds(0, 0, 900, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bahnhofstafel — Stationensuche");
        setResizable(false);

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(DB_BLUE_PRIMARY_COLOR);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 20));
        getContentPane().add(containerPanel);

        SearchPanel searchPanel = new SearchPanel();
        searchPanel.setPreferredSize(new Dimension(400, 350));
        containerPanel.add(searchPanel);

        containerPanel.add(Box.createHorizontalStrut(20));

        String[] data = {"Berlin Hauptbahnhof", "Hamburg Hbf", "Potsdam Hbf", "sdfsd", "dsfsfd", "dsfsdf", "sdfsdf"};
        SearchResultsPanel resultsPanel = new SearchResultsPanel(data);
        resultsPanel.setPreferredSize(new Dimension(400, 350));
        containerPanel.add(resultsPanel);

        SwingUtilities.invokeLater(containerPanel::requestFocusInWindow);
    }
}
