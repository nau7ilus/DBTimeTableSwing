package de.humboldtgym.dbswing;

import java.awt.*;

public final class Constants {
	private Constants() { }

	public static final Color DB_BLUE_PRIMARY_COLOR = new Color(25, 33, 118);
	public static final Color DB_BLUE_600_COLOR = new Color(12, 57, 146);

	public static final String API_LOCATIONS = "https://v5.db.transport.rest/locations?&fuzzy=true&results=10&stops=true&addresses=false&poi=false&linesOfStops=false&language=de&query=";

	public static final String SEARCH_HEADLINE = "Bahnhofstafel";
	public static final String SEARCH_ACTION_DESCRIPTION = "Bahnhof auswählen und Informationen zu Mobilität, Ausstattung und Services enthalten";
	public static final String SEARCH_HINT = "Wie zum Beispiel <b><u>Berlin Hauptbahnhof</u></b>, <b><u>Düsseldorf Hbf</u></b> oder <b><u>Hamburg Hbf</u></b>. <b><u>Schleife</u></b> ist auch ein schöner Bahnhof.";
	public static final String SEARCH_RESULTS = "Suchergebnisse:";
	public static final String SEARCH_INPUT_PLACEHOLDER = "Suche nach einem Bahnhof";
	public static final String OPEN_TABLE = "Öffnen";
}
