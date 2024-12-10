package de.humboldtgym.dbswing.Model;

import de.humboldtgym.dbswing.RESTHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static de.humboldtgym.dbswing.Constants.*;

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

    public List<Trip> getTrips() {
        return this.trips;
    }

    public void setTrips(List<Trip> newTrips) {
        trips.clear();
        newTrips.sort(Comparator.comparing(Trip::getWhenPlanned));
        trips.addAll(newTrips);
        notifyListeners();
    }

    public void loadTrips() {
        String requestEndpoint = API_TRIPS + RESTHelper.encodeQuery(this.currentStation.getId()) + API_TRIPS_QUERY;

        RESTHelper.get(requestEndpoint).thenAccept(response -> {
            List<Trip> trips = this.parseTrips(response);

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (Trip trip : trips) {
                futures.add(this.loadTripInfo(trip));
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).thenRun(() -> {
                setTrips(trips);
            }).exceptionally(ex -> {
                System.err.println("Fehler beim Abruf der Abfahrten: " + ex);
                ex.printStackTrace();
                return null;
            });
        }).exceptionally(ex -> {
            System.err.println("Fehler beim Abruf der Abfahrten: " + ex);
            ex.printStackTrace();
            return null;
        });
    }

    public CompletableFuture<Void> loadTripInfo(Trip trip) {
        String encodedId = RESTHelper.encodeQuery(trip.getId());
        String encodedLineName = RESTHelper.encodeQuery(trip.getLine().getName());
        String requestEndpoint = API_BASE_URL + "/trips/" + encodedId + "?language=de&lineName=" + encodedLineName;

        return RESTHelper.get(requestEndpoint).thenAccept(response -> {
            List<Station> stopovers = new ArrayList<>();
            JSONObject responseObj = new JSONObject(response);
            JSONArray stopoversJSONArray = responseObj.getJSONArray("stopovers");

            for (int i = 0; i < stopoversJSONArray.length(); i++) {
                JSONObject stopoverObject = stopoversJSONArray.getJSONObject(i);

                JSONObject stationRaw = stopoverObject.getJSONObject("stop");
                Station station = Station.parseJSONObject(stationRaw);

                String departureRaw = stopoverObject.optString("departure", "");
                if (!departureRaw.isEmpty()) {
                    Date departure = Date.from(Instant.parse(departureRaw));
                    station.setDeparture(departure);
                }

                stopovers.add(station);
            }
            trip.setStopovers(stopovers);
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
