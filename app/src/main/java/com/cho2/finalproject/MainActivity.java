package com.cho2.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ReservationBean reservationBean;
    private MemberBean memberBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         reservationBean = new ReservationBean();
         reservationBean.mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
         Log.e("MainActivity", "reservationBean.mEmail 값: "+reservationBean.mEmail);



        ImageView btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FifAnnivActivity.class);
                reservationBean.step1BuildName = ReservationBean.FIFTH;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        ImageView btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HumanSocietyActivity.class);
                reservationBean.step1BuildName = ReservationBean.HUMAN;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        ImageView btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Science1Activity.class);
                reservationBean.step1BuildName = ReservationBean.SCIENCE1;
                intent.putExtra("reservation", reservationBean);
                startActivity(intent);
            }
        });

        ImageView btn4 = findViewById(R.id.btn4);
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
                final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                FirebaseDatabase.getInstance().getReference().child("members").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            memberBean = snapshot.getValue(MemberBean.class);
                            if(TextUtils.equals(memberBean.userEmail, email)){
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = null;
                if(memberBean != null){
                    if(memberBean.isAdmin){
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                    }else{
                        intent = new Intent(getApplicationContext(), AdminActivity.class);
                    }
                }

                if(intent != null){
                    startActivity(intent);
                }

            }
        });




    }
}