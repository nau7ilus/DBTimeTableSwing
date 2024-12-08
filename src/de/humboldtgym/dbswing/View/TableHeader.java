package de.humboldtgym.dbswing.View;

import javax.swing.*;
import java.awt.*;

public class TableHeader extends TimetableEntryAbstract {
    public TableHeader() {
        this.init();
    }

    @Override
    protected JPanel addTimeAndTrain() {
        JPanel panel = getTimeAndTrainContainer();

        JLabel label = new JLabel("<html>Zug / <i>Train</i><br>Zeit / <i>Time</i></html>");
        label.setFont(new Font("D-DIN", Font.PLAIN, 16));
        label.setForeground(Color.white);
        panel.add(label);

        return panel;
    }

    @Override
    protected JPanel addViaDest() {
        JPanel panel = getViaDestContainer();

        JLabel label = new JLabel("<html>Über / <i>Via</i><br>Ziel / <i>Destination</i></html>");
        label.setFont(new Font("D-DIN", Font.PLAIN, 16));
        label.setForeground(Color.white);
        panel.add(label);

        return panel;
    }

    @Override
    protected JPanel addTrack() {
        JPanel panel = getTrackContainer(BoxLayout.Y_AXIS);

        JLabel label = new JLabel("<html>Gleis / <i>Track</i></html>");
        label.setFont(new Font("D-DIN", Font.PLAIN, 16));
        label.setForeground(Color.white);
        panel.add(label);
        return panel;
    }

    @Override
    protected JPanel addRemarks() {
        return getRemarksContainer();
    }
}
