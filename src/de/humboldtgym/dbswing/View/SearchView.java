package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.FancyTextField;
import de.humboldtgym.dbswing.Model.Station;

import javax.swing.*;
import java.awt.*;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_PRIMARY_COLOR;

public class SearchView extends JFrame {
    private final SearchPanel searchPanel = new SearchPanel();
    private final SearchResultsPanel resultsPanel = new SearchResultsPanel();

    public SearchView() {
        setBounds(100, 100, 900, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bahnhofstafel — Stationensuche");
        setResizable(false);

        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(DB_BLUE_PRIMARY_COLOR);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 20, 20));
        getContentPane().add(containerPanel);

        searchPanel.setPreferredSize(new Dimension(400, 350));
        containerPanel.add(searchPanel);

        containerPanel.add(Box.createHorizontalStrut(20));

        resultsPanel.setPreferredSize(new Dimension(400, 350));
        containerPanel.add(resultsPanel);

        SwingUtilities.invokeLater(containerPanel::requestFocusInWindow);
    }

    public FancyTextField getSearchField() {
        return searchPanel.getSearchInput();
    }

    public JList<Station> getResultsList() {
        return resultsPanel.getResultsList();
    }
}
