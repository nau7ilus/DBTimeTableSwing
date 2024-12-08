package de.humboldtgym.dbswing.Model;

import org.json.JSONObject;

public class Line {
    private String id;
    private String name;
    private String productName;
    private String operatorName;

    public static Line parseJSONObject(JSONObject obj) {
        Line line = new Line();
        line.id = obj.getString("fahrtNr");
        line.name = obj.getString("name");
        line.productName = obj.getString("productName");
        JSONObject operatorObject = obj.getJSONObject("operator");
        line.operatorName = operatorObject.getString("name");
        return line;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getOperatorName() {
        return this.operatorName;
    }
}
