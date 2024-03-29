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

public class Spinner3Activity extends AppCompatActivity { // 제1과 스피너


    private Spinner spinner3;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    private ReservationBean reservationBean;
    private TextView mTxtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner3);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

        // 선택된 날짜 텍스트뷰 획득
        mTxtDate = findViewById(R.id.txtSelectDate);
        mTxtDate.setText(reservationBean.step2Day);

        arrayList = new ArrayList<>();
        arrayList.add("강의실을 선택하세요");
        arrayList.add("B101호");
        arrayList.add("B102호");
        arrayList.add("B105호");
        arrayList.add("B108호");
        arrayList.add("B109호");
        arrayList.add("B111a호");
        arrayList.add("B113호");
        arrayList.add("B115호");
        arrayList.add("409호");



        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(arrayAdapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), arrayList.get(i) + "가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
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


