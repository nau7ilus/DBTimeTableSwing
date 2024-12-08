package de.humboldtgym.dbswing;

import java.awt.*;

public final class Constants {
    public static final Color DB_BLUE_PRIMARY_COLOR = new Color(25, 33, 118);
    public static final Color DB_BLUE_600_COLOR = new Color(12, 57, 146);
    public static final String API_BASE_URL = "https://v5.db.transport.rest";
    public static final String API_LOCATIONS = API_BASE_URL + "/locations?&fuzzy=true&results=10&stops=true&addresses=false&poi=false&linesOfStops=false&language=de&query=";
    public static final String API_TRIPS = API_BASE_URL + "/stops/";
    public static final String API_TRIPS_QUERY = "/departures?duration=60&bus=false&ferry=false&taxi=false&subway=false&tram=false&suburban=false&remarks=true&language=de";
    public static final String SEARCH_HEADLINE = "Bahnhofstafel";
    public static final String SEARCH_ACTION_DESCRIPTION = "Bahnhof auswählen und Informationen zu Mobilität, Ausstattung und Services enthalten";
    public static final String SEARCH_HINT = "Wie zum Beispiel <b><u>Berlin Hauptbahnhof</u></b>, <b><u>Düsseldorf Hbf</u></b> oder <b><u>Hamburg Hbf</u></b>. <b><u>Schleife</u></b> ist auch ein schöner Bahnhof.";
    public static final String SEARCH_RESULTS = "Suchergebnisse:";
    public static final String SEARCH_INPUT_PLACEHOLDER = "Suche nach einem Bahnhof";
    public static final String OPEN_TABLE = "Öffnen";
    public static final String TABLE_HEADER_TIME = "<html><i>Zug / Train<br>Zeit / Time</i></html>";
    public static final String TABLE_HEADER_VIA_DIST = "<html><i>Über / Via<br>Ziel / Destination</i></html>";
    public static final String TABLE_HEADER_TRACK = "<html><i>Gleis / Track</i></html>";
    private Constants() {
    }

}
