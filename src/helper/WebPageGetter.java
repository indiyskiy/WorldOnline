package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Илья on 13.05.14.
 */
public class WebPageGetter {
    public static String getTextFromPage(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String result = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            result += inputLine;
        }
        in.close();
        return result;
    }

}
