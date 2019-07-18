package com.cho2.finalproject.bean;

import java.io.Serializable;

public class ReservationBean implements Serializable {

    public String mReserveMonth; // 예약 월
    public String mReserveDay; // 예약 일
    public String mReserveTime; // 예약 시간
    public int mReserveBuilding; //건물
    public String mReserbeRoom;     //호실
    public MemberBean mReserveMember; // 예약한 사람

}
