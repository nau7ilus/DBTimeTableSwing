package de.humboldtgym.dbswing.Model;

import org.json.JSONObject;

import java.time.Instant;
import java.util.Date;

public class Station {
    private final String id;
    private final String name;
    private Date departure;
    private boolean hasNationalExpress;
    private boolean hasRegionalExpress;

    public Station(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDeparture() {
        return this.departure;
    }

    public boolean hasNationalExpress() {
        return hasNationalExpress;
    }

    public boolean hasRegionalExpress() {
        return hasRegionalExpress;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Station parseJSONObject(JSONObject obj) {
        String id = obj.getString("id");
        String name = obj.getString("name");

        Station station = new Station(id, name);

        JSONObject products = obj.getJSONObject("products");
        station.hasNationalExpress = products.getBoolean("nationalExpress");
        station.hasRegionalExpress = products.getBoolean("regionalExp");

        return station;
    }
}
