package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.ReservationBean;

import java.util.ArrayList;

public class Spinner2Activity extends AppCompatActivity { //50주년 스피너 - 2
    private Spinner spinner1;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private TextView mTxtDate;
    private ReservationBean reservationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner2);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

        // 선택된 날짜 텍스트뷰 획득
        mTxtDate = findViewById(R.id.txtSelectDate);
        mTxtDate.setText(reservationBean.step2Day);

        arrayList = new ArrayList<>();
        arrayList.add("강의실을 선택하세요");
        arrayList.add("402호");
        arrayList.add("403호");
        arrayList.add("404호");
        arrayList.add("405호");
        arrayList.add("406호");
        arrayList.add("407호");
        arrayList.add("408호");
        arrayList.add("409호");
        arrayList.add("411호");
        arrayList.add("412호");
        arrayList.add("413호");
        arrayList.add("414호");
        arrayList.add("420호");
        arrayList.add("421호");
        arrayList.add("422호");
        arrayList.add("423호");
        arrayList.add("514호");
        arrayList.add("515호");
        arrayList.add("520호");
        arrayList.add("522호");


        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                if(i!=0){
                    reservationBean.step3RoomName= arrayList.get(i);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirm()){
                    Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                    intent.putExtra("reservation", reservationBean);
                    startActivity(intent);}
                else return;
            }
        });
    }
    private boolean confirm(){
        boolean result=false;
        if (reservationBean.step3RoomName==null){
            Toast.makeText(this, "강의실을 선택해주세요",Toast.LENGTH_LONG).show();
            return result;
        }
        result=true;
        return result;
    }

}
