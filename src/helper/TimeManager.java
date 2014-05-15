package helper;


import model.phone.weather.WeatherTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;

public class TimeManager {
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
        String dateString = weatherTime.getYear() + "-" + weatherTime.getMonth() + "-" + weatherTime.getDay() + " " + weatherTime.getHour() + ":30:00.0";
        return Timestamp.valueOf(dateString);
    }

    public static void main(String[] args) {
        try {
            WeatherTime weatherTime = getCurrentWeatherTime();
            Timestamp timestamp = getTimestampFromWeatherTime(weatherTime);
            System.out.println(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
