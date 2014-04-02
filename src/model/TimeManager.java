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

    public static void main(String[] args) {
        try {
            Timestamp a = currentTime();
            Thread.sleep(60000);
            Timestamp b = currentTime();
            System.out.println(getDifferentInMillis(b, a));
            System.out.println(millisToMinutes(getDifferentInMillis(b, a)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
