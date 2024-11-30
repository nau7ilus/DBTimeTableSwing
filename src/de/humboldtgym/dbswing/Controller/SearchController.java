package de.humboldtgym.dbswing.Controller;

import de.humboldtgym.dbswing.Model.Station;
import de.humboldtgym.dbswing.Model.StationModel;
import de.humboldtgym.dbswing.View.SearchView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Arrays;
import java.util.List;
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
                    model.setStations(List.of());
                }
            }
        });

        model.addChangeListener(e -> updateView());
    }

    private void fetchStations(String query) {
        int token = currentToken.incrementAndGet();

        SwingWorker<List<Station>, Void> worker = new SwingWorker<List<Station>, Void>() {
            @Override
            protected List<Station> doInBackground() throws Exception {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return Arrays.asList(new Station("1", query + "1"), new Station("2", query + "2"), new Station("3", query + "2"));
            }

            @Override
            protected void done() {
                try {
                    if (token == currentToken.get()) {
                        List<Station> results = get();
                        model.setStations(results);
                    } else {
                        System.out.println("Previous request " + query + " was ignored.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void updateView() {
        DefaultListModel<Station> listModel = (DefaultListModel<Station>) view.getResultsList().getModel();
        listModel.clear();
        for (Station station : model.getStations()) {
            listModel.addElement(station);
        }
    }


}
