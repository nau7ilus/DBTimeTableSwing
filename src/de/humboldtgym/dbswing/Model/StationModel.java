package de.humboldtgym.dbswing.Model;

import de.humboldtgym.dbswing.RESTHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static de.humboldtgym.dbswing.Constants.API_LOCATIONS;

public class StationModel {
    private final AtomicInteger currentRequestToken = new AtomicInteger(0);
    private final List<Station> stations = new ArrayList<>();
    private final List<ChangeListener> listeners = new ArrayList<>();

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> newStations) {
        stations.clear();
        stations.addAll(newStations);
        notifyListeners();
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void loadStations(String query) {
        int requestToken = currentRequestToken.incrementAndGet();
        String requestEndpoint = API_LOCATIONS + RESTHelper.encodeQuery(query);

        RESTHelper.get(requestEndpoint).thenAccept(response -> {
            if (requestToken == currentRequestToken.get()) {
                List<Station> fetchedStations = parseStations(response);
                setStations(fetchedStations);
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            System.err.println("Erfolglose Server-Anfrage");
            return null;
        });
    }

    private List<Station> parseStations(String jsonResponse) {
        List<Station> stationList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stationObj = jsonArray.getJSONObject(i);
            String type = stationObj.getString("type");
            if (!type.equals("station")) continue;
            stationList.add(Station.parseJSONObject(stationObj));
        }

        return stationList;
    }

    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }
}
