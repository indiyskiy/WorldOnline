package model.textparser;

import model.FileReader;
import model.xmlparser.GlobalXmlParser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class StringFileParser {
    public static ArrayList<Integer> getIntegerListByString(String text) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        String[] strings = text.split(",");
        for (String string : strings) {
            integerArrayList.add(Integer.parseInt(string));
        }
        return integerArrayList;
    }

    public static ArrayList<Double> getDoubleListByString(String text) {
        ArrayList<Double> integerArrayList = new ArrayList<Double>();
        String[] strings = text.split(",");
        for (String string : strings) {
            integerArrayList.add(Double.parseDouble(string));
        }
        return integerArrayList;
    }


    public static ArrayList<StringIntPair> parseStandartStringIntPair(String text) {
        ArrayList<StringIntPair> pairs = new ArrayList<StringIntPair>();
        int i = 0;
        int start;
        int end = 0;
        do {
            start = end;
            end = String.valueOf(i).length() + text.indexOf("" + i, end);
            i++;
            if (text.indexOf("" + i, end) != -1) {
                String subString = text.substring(start, end);
                StringIntPair stringIntPair = getStringIntPair(subString);
                pairs.add(stringIntPair);
            }
        } while (text.indexOf("" + i, end) != -1);
        return pairs;
    }

    private static StringIntPair getStringIntPair(String subString) {
        String[] pair = subString.split(",");
        int anInt = Integer.parseInt(pair[1]);
        String string = pair[0];
        return new StringIntPair(string, anInt);
    }
}
