package com.cho2.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


    private Spinner spinner1;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private String mAdminEmail;
    private ListView adminListView;
    private List<ReservationCompleteBean> mReservationCompleatBeanList;

    // 데이터베이스 참조 객체
    FirebaseDatabase mFirebaseDatabse = FirebaseDatabase.getInstance();

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
                    List<MemberBean> memberBeans = snapshot.getValue();
                    if(memberBean.reservationCompleteList!= null){
                        // 각 멤버의 예약된 리스트가 있으면 리스트뷰에 add함
                        mReservationCompleatBeanList.addAll(memberBean.reservationCompleteList);
                    } // if
                } // for
            } //  onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }); // addListenerForSingleValueEvent

        if(mReservationCompleatBeanList != null){
            // 어댑터 생성 및 원본 데이터 설정
            MyPageAdapter myPageAdapter = new MyPageAdapter(this, mReservationCompleatBeanList);


            // 리스트뷰에 어댑터 설정
            adminListView.setAdapter(myPageAdapter);
        }


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

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"이 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




       /* findViewById(R.id.btnDetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminActivity.this);
                alertDialog.setTitle("예약정보"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:10:00~11:00\n예약을 취소하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("예약취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 예약취소하기(실제로 취소되도록 연결)
                    }
                });
                alertDialog.setNeutralButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 아무일 안일어남.
                    }
                });
                alertDialog.show();
            }
        });*/
    }
}