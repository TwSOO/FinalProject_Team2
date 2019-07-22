package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.AdminPageAdapter;
import com.cho2.finalproject.adapter.MyPageAdapter;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationCompleteBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {



    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private String mAdminEmail;
    private ListView adminListView;
    private List<ReservationCompleteBean> mReservationCompleatBeanList;

    // 데이터베이스 참조 객체
    FirebaseDatabase mFirebaseDatabse = FirebaseDatabase.getInstance();

    //리스트뷰 어댑터
    private AdminPageAdapter adminPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mAdminEmail = getIntent().getStringExtra("loginMemberEmail");
        Log.e("AdminActivity", "admin이메일 : "+mAdminEmail);

        // 예약하기 버튼 획득
        findViewById(R.id.btnReserve).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.putExtra("loginMemberEmail", mAdminEmail);
                startActivity(intent);
            }
        });

        // 리스트뷰 획득
        adminListView =findViewById(R.id.adminListView);

        // 어댑터에 설정할 예약된 데이터 목록 객체 생성
        mReservationCompleatBeanList = new ArrayList<>();

        // 어댑터에 설정될 데이터 획득
        mFirebaseDatabse.getReference().child("members").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    MemberBean memberBean = snapshot.getValue(MemberBean.class);
                    if(memberBean.reservationCompleteList!= null){
                        // 각 멤버의 예약된 리스트가 있으면 리스트뷰에 add함
                        mReservationCompleatBeanList.addAll(memberBean.reservationCompleteList);
                    } // if
                } // for


                if(mReservationCompleatBeanList != null){
                    Log.e("AdminActitivity", "mReservationCompleatBeanList null 아님");
                    // 어댑터 생성 및 원본 데이터 설정
                    adminPageAdapter  = new AdminPageAdapter(AdminActivity.this, mReservationCompleatBeanList);
                    // 리스트뷰에 어댑터 설정
                    adminListView.setAdapter(adminPageAdapter);
                }

            } //  onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); // addListenerForSingleValueEvent



        arrayList = new ArrayList<>();
        arrayList.add("7월");
        arrayList.add("8월");
        arrayList.add("9월");
        arrayList.add("10월");
        arrayList.add("11월");
        arrayList.add("12월");




        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);



    } // onCreate
}