package com.cho2.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.TImeAdapter;
import com.cho2.finalproject.bean.ReservationBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ListView listMyPage = findViewById(R.id.listMyPage);


//        mFirebaseDatabase.getReference().child("reservations").child(mReservationBean.step1BuildName)
//                .child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                ReservationBean reservationBean = dataSnapshot.getValue(ReservationBean.class);
//
//                mTimeAdapter = new TImeAdapter(ReservationActivity.this, reservationBean);
//                mListView.setAdapter(mTimeAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {}
//        });
    }


} // end class
