package helper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringHelper {
    public static String getStringFromArray(List<Object> collection, String splitter) {
        String res = "";
        if (collection.isEmpty()) {
            return res;
        }
        for (Object obj : collection) {
            res += obj + splitter;
        }
        return res.substring(0, res.lastIndexOf(splitter));
    }

    public static String getStringFromArray(List<Object> collection) {
        return getStringFromArray(collection, ",");
    }
}
