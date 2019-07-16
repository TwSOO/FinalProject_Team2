package com.cho2.finalproject.firebase;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cho2.finalproject.JoinAcitivity;
import com.cho2.finalproject.bean.MemberBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InsertFirebase {
    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance(STORAGE_DB_URL);
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    public static String getUserIdFromUUID(String userEmail){
        long val = UUID.nameUUIDFromBytes(userEmail.getBytes()).getMostSignificantBits(); // 사용자 이메일을 고유 숫자값으로 바꿈
        return String.valueOf(val);
    }
} // end InserFirebase
