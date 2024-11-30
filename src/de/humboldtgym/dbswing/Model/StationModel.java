package de.humboldtgym.dbswing.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
        new Thread(() -> {
            try {
                //noinspection CharsetObjectCanBeUsed
                String requestEndpoint = API_LOCATIONS + URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
                URL url = new URL(requestEndpoint);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        if (requestToken == currentRequestToken.get()) {
                            List<Station> fetchedStations = parseStations(response.toString());
                            setStations(fetchedStations);
                        }
                    }
                } else {
                    System.err.println("API Error: " + connection.getResponseCode());
                }

                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private List<Station> parseStations(String jsonResponse) {
        List<Station> stationList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonResponse);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject stationObj = jsonArray.getJSONObject(i);
            String type = stationObj.getString("type");
            if (!type.equals("station")) continue;
            String id = stationObj.getString("id");
            String name = stationObj.getString("name");
            stationList.add(new Station(id, name));
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
