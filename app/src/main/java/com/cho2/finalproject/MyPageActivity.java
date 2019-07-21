package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.adapter.MyPageAdapter;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPageActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private ListView mListMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mListMyPage = findViewById(R.id.listMyPage);

        String uuid = InsertFirebase.getUserIdFromUUID( FirebaseAuth.getInstance().getCurrentUser().getEmail() );

        mFirebaseDatabase.getReference().child("members").child(uuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MemberBean memberBean = dataSnapshot.getValue(MemberBean.class);

                if(memberBean.reservationCompleteList != null) {
                    MyPageAdapter myPageAdapter = new MyPageAdapter(MyPageActivity.this, memberBean.reservationCompleteList, memberBean);
                    mListMyPage.setAdapter(myPageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


} // end class
