package com.cho2.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    Button btnRes10, btnRes11, btnRes12, btnRes13, btnRes14, btnRes15, btnRes16, btnRes17, btnRes18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        //10시버튼
        findViewById(R.id.btnRes10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:10:00~11:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


        //11시버튼
        findViewById(R.id.btnRes11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:11:00~12:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });



        //12시버튼
        findViewById(R.id.btnRes12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:12:00~13:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });



        //13시버튼
        findViewById(R.id.btnRes13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:13:00~14:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


        //14시버튼
        findViewById(R.id.btnRes14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:14:00~15:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });

        //15시버튼
        findViewById(R.id.btnRes15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:15:00~16:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


        //16시버튼
        findViewById(R.id.btnRes16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:16:00~17:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


        //17시버튼
        findViewById(R.id.btnRes17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:17:00~18:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


        //18시버튼
        findViewById(R.id.btnRes18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("시간:18:00~19:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                });
                alertDialog.show();
            }
        });


    }

}










