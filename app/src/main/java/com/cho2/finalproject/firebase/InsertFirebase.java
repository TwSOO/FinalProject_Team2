package com.cho2.finalproject.firebase;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cho2.finalproject.JoinAcitivity;
import com.cho2.finalproject.ReservationActivity;
import com.cho2.finalproject.adapter.TImeAdapter;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.bean.ReservationCompleteBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InsertFirebase {
    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance(STORAGE_DB_URL);
    public static final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    public static String getUserIdFromUUID(String userEmail){
        long val = UUID.nameUUIDFromBytes(userEmail.getBytes()).getMostSignificantBits(); // 사용자 이메일을 고유 숫자값으로 바꿈
        return String.valueOf(val);
    }

/*    // 예약 취소 함수
    public static void cancelReservation(final ReservationCompleteBean reservationBean, String time, String uuid) {
        DatabaseReference dbRef = mFirebaseDatabase.getReference();

//        dbRef.child("reservations").child(reservationCompleteBean.step1BuildName).child(reservationCompleteBean.step2Day).child(reservationCompleteBean.step3RoomName).
        //사용자 찾기
        mFirebaseDatabase.getReference().child("members").child(uuid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final MemberBean memberBean = dataSnapshot.getValue(MemberBean.class);

                //기존 예약을 찾는다
                mFirebaseDatabase.getReference().child("reservations").child(reservationBean.step1BuildName)
                        .child(reservationBean.step2Day).child(reservationBean.step3RoomName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReservationBean reservationBean = dataSnapshot.getValue(ReservationBean.class);
                        if(reservationBean == null) {
                            reservationBean = reservationBean;
                        }
                        mTimeAdapter = new TImeAdapter(ReservationActivity.this, reservationBean, memberBean);
                        mListView.setAdapter(mTimeAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });

            } // onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }*/

} // end InserFirebase
