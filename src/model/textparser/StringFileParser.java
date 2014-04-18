package model.textparser;

import model.FileReader;
import model.constants.Component;
import model.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 21.10.13
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class StringFileParser {
    public static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, StringFileParser.class);

    public static ArrayList<Integer> getIntegerListByString(String text, String spliter) {
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        String[] strings = text.split(spliter);
        for (String string : strings) {
            integerArrayList.add(Integer.parseInt(string));
        }
        return integerArrayList;
    }

    public static ArrayList<Double> getDoubleListByString(String text, String spliter) {
        ArrayList<Double> integerArrayList = new ArrayList<Double>();
        String[] strings = text.split(spliter);
        for (String string : strings) {
            integerArrayList.add(Double.parseDouble(string));
        }
        return integerArrayList;
    }

    public static ArrayList<StringIntPair> parseStandardStringIntPair(String text, String splitter) {
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
                StringIntPair stringIntPair = getStringIntPair(subString, splitter);
                pairs.add(stringIntPair);
            }
        } while (text.indexOf("" + i, end) != -1);
        return pairs;
    }

    private static StringIntPair getStringIntPair(String subString, String spliter) {
        String[] pair = subString.split(spliter);
        int anInt = Integer.parseInt(pair[1]);
        String string = pair[0];
        return new StringIntPair(string, anInt);
    }

    public static HashMap<Integer, Integer> getIntIntMap(String fileRoot) {
        try {
            String[] stringIntPairs = FileReader.readFileAsString(fileRoot).split("\n");
            HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<Integer, Integer>();
            for (String stringIntPair : stringIntPairs) {
                String[] strings = stringIntPair.split(";");
                integerIntegerHashMap.put(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
            }
            return integerIntegerHashMap;
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return new HashMap<Integer, Integer>();
    }
}
