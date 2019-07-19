package com.cho2.finalproject.bean;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationBean implements Serializable {
    public static final String FIFTH = "50주년기념관";
    public static final String HUMAN = "인문사회관";
    public static final String SCIENCE1 = "제1과학관";
    public static final String SCIENCE2= "제2과학관";


    public String step1BuildName;
    public String step2Day; // 예약 일
    public String step3RoomName;
    private List<TimeBean> timeList;
    public String mEmail;//예약한 사람 이메일


    public void setTImeList(List<TimeBean> timeList) {
        this.timeList = timeList;
    }
    public List<TimeBean> getTimeList() {
        if(timeList == null) {
            timeList = new ArrayList<>();
            timeList.add( new TimeBean("09:00", false, 0) );
            timeList.add( new TimeBean("10:00", false, 0) );
            timeList.add( new TimeBean("11:00", false, 0) );
            timeList.add( new TimeBean("12:00", false, 0) );
            timeList.add( new TimeBean("13:00", false, 0) );
            timeList.add( new TimeBean("14:00", false, 0) );
            timeList.add( new TimeBean("15:00", false, 0) );
            timeList.add( new TimeBean("16:00", false, 0) );
            timeList.add( new TimeBean("17:00", false, 0) );
            timeList.add( new TimeBean("18:00", false, 0) );
        }
        return timeList;
    }

    public void makeDate(int year, int month, int dayOfMonth){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, month , dayOfMonth);
        step2Day = sdf.format(calendar.getTime());
        Log.e("makeDate()", "makeDate() 리턴값 : " + step2Day);
    }

}
