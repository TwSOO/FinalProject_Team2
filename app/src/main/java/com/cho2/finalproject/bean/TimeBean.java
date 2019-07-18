package com.cho2.finalproject.bean;

public class TimeBean {

    public String timeTitle;
    public boolean isReservation;
    public long startMilliTime; //예약시작 시간 millisecond

    public TimeBean(String timeTitle, boolean isReservation, long startMilliTime) {
        this.timeTitle = timeTitle;
        this.isReservation = isReservation;
        this.startMilliTime = startMilliTime;
    }

}
