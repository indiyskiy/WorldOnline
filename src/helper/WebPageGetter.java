package helper;

import java.io.*;
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

    public static void main(String[] args) {
        try {
            System.out.println(getTextFromPage("http://bash.im"));
            FileHelper.saveToFile(getTextFromPage("http://bash.im"), "D:/", "name.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
