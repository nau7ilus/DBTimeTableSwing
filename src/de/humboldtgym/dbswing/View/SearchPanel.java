package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.FancyTextField;
import de.humboldtgym.dbswing.WordWrapJLabel;

import javax.swing.*;
import java.awt.*;

import static de.humboldtgym.dbswing.Constants.*;

public class SearchPanel extends JPanel {
    public SearchPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addHeadline();
        addActionDescription();
        addSearchField();
        addSearchHint();

        add(Box.createVerticalGlue());
    }

    private void addHeadline() {
        WordWrapJLabel lblHeadline = new WordWrapJLabel(SEARCH_HEADLINE);
        lblHeadline.setForeground(Color.WHITE);
        lblHeadline.setFont(new Font("D-DIN", Font.BOLD, 52));
        add(lblHeadline);
        addMarginBottom(7);
    }

    private void addActionDescription() {
        WordWrapJLabel lblActionDescription = new WordWrapJLabel(SEARCH_ACTION_DESCRIPTION);
        lblActionDescription.setForeground(Color.WHITE);
        lblActionDescription.setFont(new Font("D-DIN", Font.PLAIN, 16));
        lblActionDescription.setMaximumSize(new Dimension(350, 100));
        add(lblActionDescription);
        addMarginBottom(30);
    }

    private void addSearchField() {
        Icon searchIcon = new ImageIcon("resources/images/search.png");
        FancyTextField searchField = new FancyTextField(10, SEARCH_INPUT_PLACEHOLDER, searchIcon);

        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchField.setFont(new Font("DIN-D", Font.PLAIN, 16));

        Dimension searchFieldDimension = new Dimension(350, 44);
        searchField.setMinimumSize(searchFieldDimension);
        searchField.setPreferredSize(searchFieldDimension);
        searchField.setMaximumSize(searchFieldDimension);

        add(searchField);
        addMarginBottom(10);
    }

    private void addSearchHint() {
        WordWrapJLabel lblSearchHint = new WordWrapJLabel(SEARCH_HINT);
        lblSearchHint.setMaximumSize(new Dimension(400, 100));
        lblSearchHint.setForeground(Color.WHITE);
        lblSearchHint.setFont(new Font("D-DIN", Font.PLAIN, 15));
        add(lblSearchHint);
    }

    private void addMarginBottom(int height) {
        add(Box.createVerticalStrut(height));
    }
}
