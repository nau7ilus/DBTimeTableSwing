package de.humboldtgym.dbswing.View;

import de.humboldtgym.dbswing.Constants;

import javax.swing.*;
import java.awt.*;

public class TimetableWindowHeader extends TimetableEntryAbstract {
    public TimetableWindowHeader() {
        this.setBorderColor(Constants.DB_BLUE_PRIMARY_COLOR);
        this.init();
    }

    @Override
    protected JPanel addTimeAndTrain() {
        JPanel panel = getTimeAndTrainContainer();

        JLabel currentTimeLabel = new JLabel("13:41");
        currentTimeLabel.setForeground(Color.white);
        currentTimeLabel.setFont(new Font("D-DIN", Font.BOLD, 35));
        panel.add(currentTimeLabel);

        return panel;
    }

    @Override
    protected JPanel addViaDest() {
        JPanel panel = getViaDestContainer();

        JLabel currentTimeLabel = new JLabel("<html><b>Abfahrt</b> / <i>Departure</i></html>");
        currentTimeLabel.setForeground(Color.white);
        currentTimeLabel.setFont(new Font("D-DIN", Font.PLAIN, 35));
        panel.add(currentTimeLabel);

        return panel;
    }

    @Override
    protected JPanel addTrack() {
        return getTrackContainer(BoxLayout.Y_AXIS);
    }

    @Override
    protected JPanel addRemarks() {
        return getRemarksContainer();
    }
}
