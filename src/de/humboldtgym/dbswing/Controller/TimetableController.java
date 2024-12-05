package de.humboldtgym.dbswing.Controller;

import de.humboldtgym.dbswing.Model.TimetableModel;
import de.humboldtgym.dbswing.View.TimetableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimetableController {
    private final TimetableModel model;
    private final TimetableView view;

    public TimetableController(TimetableModel model, TimetableView view) {
        this.model = model;
        this.view = view;
        model.addChangeListener(e -> updateView());
        view.retryRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.loadTrips();
            }
        });
    }

    private void updateView() {
        view.updateTrips(model.getTrips());
    }
}
