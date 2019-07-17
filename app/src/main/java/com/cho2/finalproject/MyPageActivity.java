package com.cho2.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyPageActivity.this);
                alertDialog.setTitle("예약취소"); //이것도 가운데로 정렬하고 싶음.
                alertDialog.setMessage("예약을 취소하시겠습니까?"); //가운데 정렬로 하고싶음.
                alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 취소가 되어야함.
                    }
                });
                alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 아무일도 안일어나야 함.
                    }
                });
                alertDialog.show();
            }
        });


        // 관리자페이지가 잘 되는지 보기위한 버튼일 뿐임.
        Button button;
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }


}
