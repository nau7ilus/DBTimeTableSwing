package de.humboldtgym.dbswing.Controller;

import de.humboldtgym.dbswing.Model.Station;
import de.humboldtgym.dbswing.Model.StationModel;
import de.humboldtgym.dbswing.View.SearchView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchController {
    private final StationModel model;
    private final SearchView view;

    private final AtomicInteger currentToken = new AtomicInteger(0);

    public SearchController(StationModel model, SearchView view) {
        this.model = model;
        this.view = view;

        view.getSearchField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onTextChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onTextChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onTextChange();
            }

            private void onTextChange() {
                String query = view.getSearchField().getText();
                boolean showingPlaceholder = view.getSearchField().getShowingPlaceholder();
                if (!query.isEmpty() && !showingPlaceholder) {
                    fetchStations(query);
                } else {
                    //noinspection ArraysAsListWithZeroOrOneArgument
                    model.setStations(Arrays.asList());
                }
            }
        });

        model.addChangeListener(e -> updateView());
    }

    private void fetchStations(String query) {
        new Thread(() -> {
            model.loadStations(query);
        }).start();
    }

    private void updateView() {
        DefaultListModel<Station> listModel = (DefaultListModel<Station>) view.getResultsList().getModel();
        listModel.clear();
        for (Station station : model.getStations()) {
            listModel.addElement(station);
        }
    }
}
