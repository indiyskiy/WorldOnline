package helper;


import model.constants.Component;
import model.constants.databaseenumeration.DayTime;
import model.logger.LoggerFactory;
import model.weather.WeatherTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

public class TimeManager {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, TimeManager.class);

    public static Long getDifferentInMillis(Timestamp minuend, Timestamp subtractor) {
        Long a = minuend.getTime();
        Long b = subtractor.getTime();
        return a - b;
    }

    public static Long millisToMinutes(Long millis) {
        if (millis == null) {
            return null;
        }
        return millis / 1000L / 60L;
    }

    public static Timestamp currentTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static WeatherTime getCurrentWeatherTime() {
        Timestamp timestamp = currentTime();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        WeatherTime weatherTime = new WeatherTime();
        weatherTime.setDay(cal.get(Calendar.DAY_OF_MONTH));
        weatherTime.setHour(cal.get(Calendar.HOUR_OF_DAY));
        weatherTime.setMonth(cal.get(Calendar.MONTH));
        weatherTime.setYear(cal.get(Calendar.YEAR));
        return weatherTime;
    }

    public static Timestamp getTimestampFromWeatherTime(WeatherTime weatherTime) throws ParseException {
        String dateString = weatherTime.getYear() + "-" + weatherTime.getMonth() + "-" + weatherTime.getDay() + " " + weatherTime.getHour() + ":00:00.0";
        return Timestamp.valueOf(dateString);
    }

    public static DayTime parseTimestamp(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        int hour = (cal.get(Calendar.HOUR_OF_DAY));
        if (hour >= 22 || hour <= 6) {
            return DayTime.Night;
        }
        if (hour >= 7 && hour <= 11) {
            return DayTime.Morning;
        }
        if (hour >= 12 && hour <= 17) {
            System.out.println("ok " + hour);
            return DayTime.Day;
        }
        if (hour >= 18 && hour <= 21) {
            return DayTime.Evening;
        }
        return DayTime.Day;
    }

    public static DayTime currentDayTime() {
        return parseTimestamp(currentTime());
    }

    public static Timestamp getTimestampFromExchangeTime(String date) {
        try {
            String[] dateArray = date.split("\\.");
            String dateString = dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0] + " " + "00:00:00.0";
            return Timestamp.valueOf(dateString);
        } catch (Exception e) {
            loggerFactory.error(e);
            loggerFactory.error("error at date " + date);
            throw e;
        }
    }

    public static void main(String[] args) {
        System.out.println(currentDayTime());
        System.out.println(currentTime());
    }


    public static Timestamp getTimestamp(String time) {
        try {
            String dateString = time + ":00.0";
            return Timestamp.valueOf(dateString);
        } catch (Exception e) {
            loggerFactory.error(e);
            loggerFactory.error("error at date " + time);
            throw e;
        }
    }
}
