package de.humboldtgym.dbswing.Model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class StationModel {
    private final List<Station> stations = new ArrayList<>();
    private final List<ChangeListener> listeners = new ArrayList<>();

    public void setStations(List<Station> newStations) {
        stations.clear();
        stations.addAll(newStations);
        notifyListeners();
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }
}
