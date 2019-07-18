package com.cho2.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.ReservationBean;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ReservationBean reservationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         reservationBean = new ReservationBean();
         reservationBean.mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
         Log.e("MainActivity", "reservationBean.mEmail 값: "+reservationBean.mEmail);



        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifAnnivActivity.class);
                reservationBean.step1BuildName = ReservationBean.FIFTH;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HumanSocietyActivity.class);
                reservationBean.step1BuildName = ReservationBean.HUMAN;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Science1Activity.class);
                reservationBean.step1BuildName = ReservationBean.SCIENCE1;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Science2Activity.class);
                reservationBean.step1BuildName = ReservationBean.SCIENCE2;
                intent.putExtra("reservation", reservationBean);
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