package com.cho2.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.TImeAdapter;
import com.cho2.finalproject.bean.ReservationBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReservationActivity extends AppCompatActivity {

    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private ReservationBean mReservationBean;
    private TImeAdapter mTimeAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mListView = findViewById(R.id.listView);

        mReservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+mReservationBean.toString());

        FirebaseDatabase.getInstance().getReference().child("reservations").child(mReservationBean.step1BuildName)
                .child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReservationBean reservationBean = dataSnapshot.getValue(ReservationBean.class);

                mTimeAdapter = new TImeAdapter(ReservationActivity.this, reservationBean);
                mListView.setAdapter(mTimeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

    }

} // end class










