package com.cho2.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.TImeAdapter;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReservationActivity extends AppCompatActivity {

    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private ReservationBean mReservationBean;
    private TImeAdapter mTimeAdapter;
    private ListView mListView;

    // 선택된 사항 표시하는 텍스트뷰
    private TextView mTxtBuilding;
    private TextView mTxtDate;
    private TextView mTxtRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        mListView = findViewById(R.id.listView);

        mReservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+mReservationBean.toString());

        String uuid = InsertFirebase.getUserIdFromUUID( FirebaseAuth.getInstance().getCurrentUser().getEmail() );
        //사용자 찾기
        mFirebaseDatabase.getReference().child("members").child(uuid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final MemberBean memberBean = dataSnapshot.getValue(MemberBean.class);

                //기존 예약을 찾는다
                mFirebaseDatabase.getReference().child("reservations").child(mReservationBean.step1BuildName)
                        .child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReservationBean reservationBean = dataSnapshot.getValue(ReservationBean.class);
                        if(reservationBean == null) {
                            reservationBean = mReservationBean;
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
        // 뷰 획득
        mTxtBuilding = findViewById(R.id.txtBuilding);
        mTxtDate = findViewById(R.id.txtDate);
        mTxtRoom = findViewById(R.id.txtRoom);

        // 앞에서 선택된 사항 보여주기
        mTxtBuilding.setText(mReservationBean .step1BuildName);
        mTxtDate.setText(mReservationBean .step2Day);
        mTxtRoom.setText(mReservationBean .step3RoomName);

    }

} // end class










