package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.ReservationBean;

import java.util.ArrayList;

public class Spinner2Activity extends AppCompatActivity { //50주년 스피너 - 2
    private Spinner spinner1;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    private ReservationBean reservationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner2);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

        arrayList = new ArrayList<>();
        arrayList.add("강의실을 선택하세요");
        arrayList.add("402");
        arrayList.add("403");
        arrayList.add("404");
        arrayList.add("405");
        arrayList.add("406");
        arrayList.add("407");
        arrayList.add("408");
        arrayList.add("409");
        arrayList.add("411");
        arrayList.add("412");
        arrayList.add("413");
        arrayList.add("414");
        arrayList.add("420");
        arrayList.add("421");
        arrayList.add("422");
        arrayList.add("423");
        arrayList.add("514");
        arrayList.add("515");
        arrayList.add("520");
        arrayList.add("522");




        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner1.setAdapter(arrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

    }
}
