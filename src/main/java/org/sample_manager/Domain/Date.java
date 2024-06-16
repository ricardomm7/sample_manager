package org.sample_manager.Domain;

import java.io.Serializable;

public class Date implements Serializable {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        } else {
            System.out.println("The given year is invalid.");
        }
    }

    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            System.out.println("Invalid month. Must be between 1 and 12.");
        }
    }

    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            System.out.println("Invalid day. It must be between 1 and 31.");
        }
    }

    public boolean isAfter(Date otherDate) {
        if (this.year > otherDate.getYear()) {
            return true;
        } else if (this.year < otherDate.getYear()) {
            return false;
        } else {
            if (this.month > otherDate.getMonth()) {
                return true;
            } else if (this.month < otherDate.getMonth()) {
                return false;
            } else {
                return this.day > otherDate.getDay();
            }
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
}