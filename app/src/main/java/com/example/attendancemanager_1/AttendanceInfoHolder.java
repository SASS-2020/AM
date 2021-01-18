package com.example.attendancemanager_1;

public class AttendanceInfoHolder {
    private String date;
    private String regd;

    public AttendanceInfoHolder() {

    }

    public AttendanceInfoHolder(String date, String regd) {
        this.date = date;
        this.regd = regd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegd() {
        return regd;
    }

    public void setRegd(String regd) {
        this.regd = regd;
    }
}
