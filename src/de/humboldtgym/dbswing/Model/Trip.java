package de.humboldtgym.dbswing.Model;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Trip {
    private final String id;
    private Date when;
    private Date whenPlanned;
    private int delay;
    private String platform;
    private String plannedPlatform;
    private Station destination;
    private boolean cancelled;
    private List<Station> via;
    private Line line;

    public Trip(String id) {
        this.id = id;
    }

    public static Trip parseJSONObject(JSONObject jsonObject) {
        String id = jsonObject.getString("when");
        Trip trip = new Trip(id);

        String whenRaw = jsonObject.optString("when", "");
        String whenPlannedRaw = jsonObject.optString("plannedWhen", "");
        trip.when = Date.from(Instant.parse(whenRaw));
        trip.whenPlanned = Date.from(Instant.parse(whenPlannedRaw));
        trip.delay = jsonObject.optInt("delay", 0);
        trip.platform = jsonObject.optString("platform", "");
        trip.plannedPlatform = jsonObject.optString("plannedPlatform", "");
        trip.destination = Station.parseJSONObject(jsonObject.getJSONObject("destination"));
        trip.cancelled = jsonObject.optBoolean("cancelled", false);
        trip.line = Line.parseJSONObject(jsonObject.getJSONObject("line"));

        return trip;
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

    public String getFormattedWhenPlanned() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(this.whenPlanned);
    }

    public String getFormattedWhen() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(this.when);
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
