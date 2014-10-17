package helper;


import java.io.UnsupportedEncodingException;
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

    public static String convertEncoding(byte[] bytes, String from, String to) throws UnsupportedEncodingException {
        return new String(new String(bytes, from).getBytes(to));
    }


    public static String convertEncoding(String string, String from, String to) throws UnsupportedEncodingException {
        return convertEncoding(string.getBytes(), from, to);
    }
}
