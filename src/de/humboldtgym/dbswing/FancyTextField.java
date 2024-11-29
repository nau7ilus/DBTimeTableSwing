package de.humboldtgym.dbswing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FancyTextField extends JTextField {

    private final int ICON_CONTAINER_WIDTH = 40;
    private final int radius;
    private final Icon icon;
    private boolean showingPlaceholder = true;

    public FancyTextField(int radius, String placeholder, Icon icon) {
        this.radius = radius;
        this.icon = icon;

        setOpaque(false);
        setForeground(Color.gray);
        setText(placeholder);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setForeground(Color.black);
                    setText("");
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.gray);
                    setText(placeholder);
                    showingPlaceholder = true;
                }
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        // Increase render quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        // Draw borders
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

        if (icon != null) {
            int iconY = (getHeight() - icon.getIconHeight()) / 2;
            int iconX = (ICON_CONTAINER_WIDTH - icon.getIconWidth()) / 2;
            icon.paintIcon(this, g, iconX, iconY);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void setBorder(Border border) {
        super.setBorder(BorderFactory.createEmptyBorder(10, ICON_CONTAINER_WIDTH, 10, 10));
    }
}
