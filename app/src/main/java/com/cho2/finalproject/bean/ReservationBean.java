package com.cho2.finalproject.bean;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


    public String makeDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Integer.parseInt(mReserveMonth), Integer.parseInt(mReserveDay));

        String date = sdf.format(calendar.getTime());
        Log.e("makeDate()", "makeDate() 리턴값 : " + date);
        return date;
    }

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
