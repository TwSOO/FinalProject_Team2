package com.cho2.finalproject.bean;

import java.io.Serializable;

public class ReservationBean implements Serializable {
    public static final int FIFTH = 1;
    public static final int HUMAN = 2;
    public static final int SCIENCE1 = 3;
    public static final int SCIENCE2= 4;

    public String mReserveMonth; // 예약 월
    public String mReserveDay; // 예약 일
    public String mReserveTime; // 예약 시간
    public int mReserveBuilding; //건물
    public String mReserveRoom;     //호실
    public String mEmail;//예약한 사람 이메일


    @Override
    public String toString() {
        return "ReservationBean{" +
                "mReserveMonth='" + mReserveMonth + '\'' +
                ", mReserveDay='" + mReserveDay + '\'' +
                ", mReserveTime='" + mReserveTime + '\'' +
                ", mReserveBuilding=" + mReserveBuilding +
                ", mReserveRoom='" + mReserveRoom + '\'' +
                ", mReserveMember=" + mEmail +
                '}';
    }

}
