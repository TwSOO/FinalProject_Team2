package com.cho2.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.FifAnnivActivity;
import com.cho2.finalproject.HumanSocietyActivity;
import com.cho2.finalproject.MyPageActivity;
import com.cho2.finalproject.R;
import com.cho2.finalproject.Science1Activity;
import com.cho2.finalproject.Science2Activity;
import com.cho2.finalproject.bean.ReservationBean;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ReservationBean reservationBean = new ReservationBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifAnnivActivity.class);
                reservationBean.building = 1;
                intent.putExtra(ReservationBean.class.getName(), reservationBean);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HumanSocietyActivity.class);
                reservationBean.building = 2;
                intent.putExtra(ReservationBean.class.getName(), reservationBean);
                startActivity(intent);
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Science1Activity.class);
                reservationBean.building = 3;
                intent.putExtra(ReservationBean.class.getName(), reservationBean);
                startActivity(intent);
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Science2Activity.class);
                reservationBean.building = 4;
                intent.putExtra(ReservationBean.class.getName(), reservationBean);
                startActivity(intent);
            }
        });

        Button btnMyPage = findViewById(R.id.btnMyPage);
        btnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });

    }
}