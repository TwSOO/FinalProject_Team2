package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FifAnnivActivity extends AppCompatActivity {

    CalendarView calendar;
    TextView tvDate;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fif_anniv);

        calendar = (CalendarView) findViewById(R.id.calendar);
        tvDate = (TextView) findViewById(R.id.tvDate);
        btnNext = (Button) findViewById(R.id.btnNext);

        //리스너 등록
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                String txtDate = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                tvDate.setText(txtDate);

            }
        });


        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Spinner2Activity.class);
                startActivity(intent);
            }
        });
    }
}