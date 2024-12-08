package de.humboldtgym.dbswing;

import javax.swing.*;
import java.awt.*;

public class MarqueeLabel extends JPanel {
    private final String text;
    private final int speed;
    private int x;
    private int textWidth;

    public MarqueeLabel(String text, int speed) {
        this.text = text;
        this.speed = speed;
        this.x = 0;

        Timer timer = new Timer(20, e -> updateMarquee());
        timer.start();
    }

    private void updateMarquee() {
        x -= speed;
        if (x + textWidth < 0) {
            x = getWidth();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (textWidth == 0) {
            textWidth = g.getFontMetrics().stringWidth(text);
        }

        g.setFont(getFont());
        g.setColor(getForeground());

        g.drawString(text, x, getHeight() / 2 + g.getFontMetrics().getAscent() / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 50);
    }
}