package de.humboldtgym.dbswing;

import javax.swing.*;
import java.awt.*;

public class ContentWrapperPanel extends JPanel {
    private final JPanel contentPanel = new JPanel();

    public ContentWrapperPanel(int align, String fixed) {
        setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, align));
        add(contentPanel, fixed);
    }

    public JPanel getContentPanel() {
        return this.contentPanel;
    }

    @Override
    public Component add(Component component) {
        contentPanel.add(component);
        return component;
    }
}
