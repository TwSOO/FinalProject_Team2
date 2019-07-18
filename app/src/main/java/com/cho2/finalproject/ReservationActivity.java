package com.cho2.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class ReservationActivity extends AppCompatActivity {

    Button btnRes10, btnRes11, btnRes12, btnRes13, btnRes14, btnRes15, btnRes16, btnRes17, btnRes18;
    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private String mReserveTime;
    private String mReserveMonth; // 예약 월
    private String mReserveDay; // 예약 일
    private int mReserveBuilding; //건물
    private String mReserbeRoom;     //호실
    private MemberBean mReserveMember; // 예약한 사람
    private ReservationBean reservationBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        reservationBean = (ReservationBean)getIntent().getSerializableExtra("reservation");
        Log.e("reservationBean", "reservationBean 내용"+reservationBean.toString());

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


    private void reservaion(ReservationBean reservationBean){

        DatabaseReference dbRef=mFirebaseDatabase.getReference();

        dbRef.child("members").child(uerUUID).setValue(reservationBean);
        Toast.makeText(this,"예약 완료",Toast.LENGTH_LONG).show();

    }

} // end class










