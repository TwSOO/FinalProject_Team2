package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.ReservationBean;

public class Science2Activity extends AppCompatActivity {

    CalendarView calendar;
    TextView tvDate;
    Button btnNext;
    private ReservationBean reservationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science2);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

        calendar = (CalendarView) findViewById(R.id.calendar);
        tvDate = (TextView) findViewById(R.id.tvDate);
        btnNext = (Button) findViewById(R.id.btnNext);

        //리스너 등록
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                reservationBean.mReserveMonth = Integer.toString(month + 1);
                reservationBean.mReserveDay = Integer.toString(dayOfMonth);

                String txtDate = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                tvDate.setText(txtDate);

            }
        });

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Spinner4Activity.class);
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });
    }
}
