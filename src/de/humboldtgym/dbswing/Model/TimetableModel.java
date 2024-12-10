package de.humboldtgym.dbswing.Model;

import de.humboldtgym.dbswing.RESTHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
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
    private final Timer updateTimer;

    public TimetableModel(Station station) {
        this.currentStation = station;

        updateTimer = new Timer(30 * 1000, e -> {
            this.loadTrips();
            System.out.println("Update");
        });

        updateTimer.start();
    }

    public void stopUpdating() {
        updateTimer.stop();
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
        int maxRetries = 5;
        long retryDelay = 2000;

        retryLoadTrips(API_TRIPS + RESTHelper.encodeQuery(this.currentStation.getId()) + API_TRIPS_QUERY, maxRetries, retryDelay).thenAccept(trips -> {
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
        });
    }

    private CompletableFuture<List<Trip>> retryLoadTrips(String endpoint, int maxRetries, long retryDelay) {
        CompletableFuture<List<Trip>> future = new CompletableFuture<>();

        attemptLoadTrips(endpoint, 0, maxRetries, retryDelay, future);

        return future;
    }

    private void attemptLoadTrips(String endpoint, int attempt, int maxRetries, long retryDelay, CompletableFuture<List<Trip>> future) {
        RESTHelper.get(endpoint).thenApply(this::parseTrips).thenAccept(future::complete)
                .exceptionally(ex -> {
                    if (isServerError(ex) && attempt < maxRetries - 1) {
                        System.err.println("Fehler beim Abruf der Abfahrten (Versuch " + (attempt + 1) + "): " + ex);
                        scheduleRetry(endpoint, attempt + 1, maxRetries, retryDelay, future);
                    } else {
                        future.completeExceptionally(ex);
                    }
                    return null;
                });
    }

    private void scheduleRetry(String endpoint, int attempt, int maxRetries, long retryDelay, CompletableFuture<List<Trip>> future) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(retryDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                future.completeExceptionally(e);
            }
        }).thenRun(() -> attemptLoadTrips(endpoint, attempt, maxRetries, retryDelay, future));
    }

    private boolean isServerError(Throwable ex) {
        return ex.getMessage().contains("502");
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
