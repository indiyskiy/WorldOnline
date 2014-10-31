package model.additionalentity.phone;


import com.google.gson.JsonObject;

import java.sql.Timestamp;

public class MobileUrgencyTime {
    private Timestamp start;
    private Timestamp end;

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Timestamp getEnd() {
        return end;
    }

    public JsonObject getJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", start.toString());
        jsonObject.addProperty("end", end.toString());
        return jsonObject;
    }
}
