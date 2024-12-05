package de.humboldtgym.dbswing.Model;

import de.humboldtgym.dbswing.RESTHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

import static de.humboldtgym.dbswing.Constants.API_TRIPS;
import static de.humboldtgym.dbswing.Constants.API_TRIPS_QUERY;

public class TimetableModel {
    private final List<Trip> trips = new ArrayList<>();
    private final Station currentStation;
private final List<ChangeListener> listeners = new ArrayList<>();

    public TimetableModel(Station station) {
        this.currentStation = station;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public List<Trip> getTrips() { return this.trips; }

    public void setTrips(List<Trip> newTrips) {
        trips.clear();
        trips.addAll(newTrips);
        notifyListeners();
    }

    public void loadTrips() {
        String requestEndpoint = API_TRIPS + RESTHelper.encodeQuery(this.currentStation.getId()) + API_TRIPS_QUERY;
        RESTHelper.get(requestEndpoint).thenAccept( response -> {
            List<Trip> trips = this.parseTrips(response);
            setTrips(trips);
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

    private List<Trip> parseTrips(String response) {
        List<Trip> trips = new ArrayList<>();
        JSONArray departuresArray = new JSONArray(response);
//        JSONObject jsonObject = new JSONObject(response);
//        JSONArray departuresArray = jsonObject.getJSONArray("departures");
        for (int i = 0; i < departuresArray.length(); i++) {
            JSONObject tripObj = departuresArray.getJSONObject(i);
            trips.add(Trip.parseJSONObject(tripObj));
        }
        return trips;
    }

    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }
}
