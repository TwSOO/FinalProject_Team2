package com.cho2.finalproject.bean;

import java.io.Serializable;

public class ReservationCompleteBean implements Serializable {

    public String step1BuildName;
    public String step2Day; // 예약 일
    public String step2Time; //예약 시간
    public String step3RoomName;
    public int timeIndex;
    public String mEmail;//예약한 사람 이메일

}
