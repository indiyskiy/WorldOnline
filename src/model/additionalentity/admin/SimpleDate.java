package model.additionalentity.admin;

import helper.TimeManager;
import model.constants.Month;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class SimpleDate {
    Integer year;
    Month month;
    Integer day;
    Integer hour;
    Integer minute;

    public SimpleDate(Integer year, Month month, Integer day, Integer hour, Integer minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public SimpleDate(Timestamp timestamp) {
        String[] split = timestamp.toString().split(" ");
        String[] p1 = split[0].split("-");
        String[] p2 = split[1].split(":");
        year = Integer.parseInt(p1[0]);
        month = Month.parseInt(Integer.parseInt(p1[1]));
        day = Integer.parseInt(p1[2]);
        hour = Integer.parseInt(p2[0]);
        minute = Integer.parseInt(p2[1]);
    }

    public SimpleDate(HttpServletRequest request, String prefix) {
        this.year = Integer.parseInt(request.getParameter(prefix + "Year"));
        this.month = Month.parseInt(Integer.parseInt(request.getParameter(prefix + "Month")));
        this.day = Integer.parseInt(request.getParameter(prefix + "Day"));
        this.hour = Integer.parseInt(request.getParameter(prefix + "Hour"));
        this.minute = Integer.parseInt(request.getParameter(prefix + "Minute"));
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String toString() {
        return day + " " + month.getName() + " " + year + " " + hour + ":" + minute;
    }

    public Timestamp getTimestamp() {
        String date = "";
        date += year;
        date += "-";
        if (month.getValue() < 10) {
            date += "0";
        }
        date += month.getValue();
        date += "-";
        if (day < 10) {
            date += "0";
        }
        date += day;
        date += " ";
        if (hour < 10) {
            date += "0";
        }
        date += hour;
        date += ":";
        if (minute < 10) {
            date += "0";
        }
        date += minute;
        date += ":" + "00.0";
        return Timestamp.valueOf(date);
    }

    public static void main(String[] args) {
        SimpleDate simpleDate = new SimpleDate(TimeManager.currentTime());
        System.out.println(simpleDate);
        System.out.println(simpleDate.getTimestamp());

    }
}
