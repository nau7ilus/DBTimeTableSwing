package de.humboldtgym.dbswing.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Trip {
    private final String id;
    private Date when = null;
    private Date whenPlanned = null;
    private int delay;
    private String platform;
    private String plannedPlatform;
    private Station destination;
    private boolean cancelled;
    private List<Station> stopovers;
    private Line line;
    private List<String> remarks;

    public Trip(String id) {
        this.id = id;
    }

    public static Trip parseJSONObject(JSONObject jsonObject) {
        String id = jsonObject.getString("tripId");
        Trip trip = new Trip(id);

        String whenRaw = jsonObject.optString("when", "");
        String whenPlannedRaw = jsonObject.optString("plannedWhen", "");
        trip.when = !whenRaw.isEmpty() ? Date.from(Instant.parse(whenRaw)) : null;
        trip.whenPlanned = !whenPlannedRaw.isEmpty() ? Date.from(Instant.parse(whenPlannedRaw)) : null;
        trip.delay = jsonObject.optInt("delay", 0);
        trip.platform = jsonObject.optString("platform", "");
        trip.plannedPlatform = jsonObject.optString("plannedPlatform", "");
        trip.destination = Station.parseJSONObject(jsonObject.getJSONObject("destination"));
        trip.cancelled = jsonObject.optBoolean("cancelled", false);
        trip.line = Line.parseJSONObject(jsonObject.getJSONObject("line"));

        List<String> remarks = new ArrayList<>();
        JSONArray remarksRaw = jsonObject.getJSONArray("remarks");
        for (int i = 0; i < remarksRaw.length(); i++) {
            JSONObject remarkRawJSONObject = remarksRaw.getJSONObject(i);
            remarks.add(remarkRawJSONObject.getString("text"));
        }
        trip.remarks = remarks;

        return trip;
    }

    public List<Station> getStopovers() {
        return this.stopovers;
    }

    public void setStopovers(List<Station> stopovers) {
        this.stopovers = stopovers;
    }

    public String getRelevantStopoverNames() {
        if (stopovers == null) return "...";
//       List<Station> filteredStopovers = this.stopovers.stream().filter((so) -> {
//            boolean isEligibleProduct = so.hasNationalExpress() || so.hasRegionalExpress();
//            // TODO: Ignore all station before current
//            return isEligibleProduct; // && index > currentStationIndex;
//        }).toList();

        return stopovers.stream().map(Station::getName).collect(Collectors.joining(" — "));
    }

    public String getId() {
        return this.id;
    }

    public Date getWhen() {
        return this.when;
    }

    public Date getWhenPlanned() {
        return this.whenPlanned;
    }

    public List<String> getRemarks() {
        return remarks;
    }

    public String getFormattedWhenPlanned() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (whenPlanned.toString().isEmpty()) return null;
        return sdf.format(this.whenPlanned);
    }

    public String getFormattedWhen() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(this.when);
        } catch (Exception ex) {
            return "";
        }
    }

    public int getDelay() {
        return this.delay;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getPlannedPlatform() {
        return this.plannedPlatform;
    }

    public Station getDestination() {
        return this.destination;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public Line getLine() {
        return this.line;
    }
}
