package model;


import java.sql.Timestamp;

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

}
