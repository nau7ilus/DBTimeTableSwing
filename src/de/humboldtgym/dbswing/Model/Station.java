package de.humboldtgym.dbswing.Model;

import org.json.JSONObject;

public class Station {
    private final String id;
    private final String name;

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

    @Override
    public String toString() {
        return this.name;
    }

    public static Station parseJSONObject(JSONObject obj) {
        String id = obj.getString("id");
        String name = obj.getString("name");
        return new Station(id, name);
    }
}
