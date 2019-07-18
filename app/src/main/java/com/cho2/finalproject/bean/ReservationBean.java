package com.cho2.finalproject.bean;

import java.io.Serializable;

public class ReservationBean implements Serializable {
    public static final String FIFTH = "50주년기념관";
    public static final String HUMAN = "인문사회관";
    public static final String SCIENCE1 = "제1과학관";
    public static final String SCIENCE2= "제2과학관";

    public String mReserveMonth; // 예약 월
    public String mReserveDay; // 예약 일
    public String mReserveTime; // 예약 시간
    public String mReserveBuilding; //건물
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
