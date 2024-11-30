package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.Model.Station;

import javax.swing.*;
import java.awt.*;

import static de.humboldtgym.dbswing.Constants.OPEN_TABLE;
import static de.humboldtgym.dbswing.Constants.SEARCH_RESULTS;

public class SearchResultsPanel extends JPanel {
    private JList<Station> resultsList;
    private JButton submitButton;

    public SearchResultsPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addTitle();
        addResultsList();
        addSubmitButton();
    }

    private void addTitle() {
        JLabel resultsLabel = new JLabel(SEARCH_RESULTS);
        resultsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultsLabel.setForeground(Color.WHITE);
        resultsLabel.setFont(new Font("D-DIN", Font.ITALIC, 16));
        add(resultsLabel);
        addMarginBottom(5);
    }

    private void addResultsList() {
        resultsList = new JList<>(new DefaultListModel<>());
        resultsList.setFont(new Font("DIN-D", Font.PLAIN, 20));
        resultsList.setFixedCellWidth(getWidth());
        resultsList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane resultsScrollPane = new JScrollPane(resultsList);
        resultsScrollPane.setMaximumSize(new Dimension(400, 200));
        resultsScrollPane.setPreferredSize(new Dimension(400, 150));
        resultsScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(resultsScrollPane);
        addMarginBottom(15);
    }

    private void addSubmitButton() {
        submitButton = new JButton(OPEN_TABLE);
        submitButton.setFont(new Font("DIN-D", Font.PLAIN, 20));
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.setMinimumSize(new Dimension(400, 40));
        add(submitButton);
        add(Box.createVerticalGlue());
    }

    private void addMarginBottom(int height) {
        add(Box.createVerticalStrut(height));
    }

    public JList<Station> getResultsList() {
        return this.resultsList;
    }

    public JButton getSubmitButton(){
        return this.submitButton;
    }
}
