package com.cho2.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.TImeAdapter;
import com.cho2.finalproject.bean.ReservationBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservationActivity extends AppCompatActivity {

    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private ReservationBean reservationBean;
    private TImeAdapter mTimeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

        ListView listView = findViewById(R.id.listView);
        mTimeAdapter = new TImeAdapter(this, reservationBean);
        listView.setAdapter(mTimeAdapter);

    }

} // end class










