package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Timestamp;

public class JSONParserTest {
    public static void main(String[] args) {
        String body = "{\"cardActivities\":[{\"cardID\":3308,\"onTimestamp\":\"2015-01-05 19:30:00.0\",\"offTimestamp\":\"2015-01-05 22:00:00.0\"}]}";
        JsonObject mainObject = new JsonParser().parse(body).getAsJsonObject();
        JsonArray jsonArray = mainObject.getAsJsonArray("cardActivities");
        for (JsonElement jsonCardActivityElement : jsonArray) {
            JsonObject jsonCardActivity = jsonCardActivityElement.getAsJsonObject();
            String stringCardID = jsonCardActivity.get("cardID").toString();
            String stringOnTimestamp = jsonCardActivity.get("onTimestamp").toString();
            String stringOffTimestamp = jsonCardActivity.get("offTimestamp").toString();
            stringOnTimestamp = stringOnTimestamp.substring(1, stringOnTimestamp.length() - 1);
            stringOffTimestamp = stringOffTimestamp.substring(1, stringOffTimestamp.length() - 1);
            System.out.println(stringCardID);
            System.out.println(stringOnTimestamp);
            System.out.println(stringOffTimestamp);
            long cardID = Long.parseLong(stringCardID);
            Timestamp onTimestamp = Timestamp.valueOf(stringOnTimestamp);
            Timestamp offTimestamp = Timestamp.valueOf(stringOffTimestamp);
            System.out.println(onTimestamp.toString());
            System.out.println(offTimestamp.toString());
        }
    }
}
