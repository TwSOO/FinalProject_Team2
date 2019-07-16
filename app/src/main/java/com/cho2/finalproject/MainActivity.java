package com.cho2.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cho2.finalproject.bean.MemberBean;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    // 구글 로그인 클라이언트 제어자
    private GoogleSignInClient mGoogleSignInClient;
    // Firebase  인증객체
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    // TAG
    private final String TAG = getClass().getSimpleName();
    // Firebase 데이터베이스 참조 객체
    FirebaseDatabase mFirebaseDatabse = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnGoogleSignIn).setOnClickListener(mClicks);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // 구글 로그인 클라이언트 제어자 생성
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mFirebaseAuth.getCurrentUser() != null && mFirebaseAuth.getCurrentUser().getEmail() != null){
            // 이미 로그인 되어있다 따라서 메인화면으로 바로 이동한다.
            Toast.makeText(this, "로그인 성공 - 메인화면 이동", Toast.LENGTH_LONG).show();
            goJoinActivity();
        }
    } // end onResume

    private void goJoinActivity(){
            Intent intent=new Intent(this,JoinAcitivity.class);
            startActivity(intent);
            finish();
    } // end goJoinActivity
    private View.OnClickListener mClicks = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGoogleSignIn:
                    googleSignIn();
                    break;
            }
        }
    }; // mClicks


    // 구글 로그인 처리
    private void googleSignIn(){
        Intent i = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(i, 1004);
    } // end googleSignIn

    // Firebase 회원가입하면서 로그인까지 되는 것
    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Firebase 로그인 성공
                            Toast.makeText(getBaseContext(), "Firebase 로그인 성공", Toast.LENGTH_LONG).show();
                            Log.d(TAG, " >> Firebase 로그인 성공");


                        }
                        else{
                            // 로그인 실패
                            Toast.makeText(getBaseContext(), "Firebase 로그인 실패", Toast.LENGTH_LONG).show();
                            Log.e(TAG, " >> 인증 실패"+task.getException());
                        }
                    }
                });
    } // end firebaseAuthWithGoogle

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //구글 로그인 버튼 응답
        if(requestCode == 1004){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                // 구글 로그인 성공
                final GoogleSignInAccount account = task.getResult(ApiException.class);
                Toast.makeText(getBaseContext(), "구글 로그인에 성공 하였습니다.", Toast.LENGTH_LONG).show();

                //Firebase에 계정 존재 여부 체크
                final String userEmail = account.getEmail();
                mFirebaseDatabse.getReference().child("members").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            MemberBean memberBean = snapshot.getValue(MemberBean.class);
                            if(TextUtils.equals(memberBean.userEmail, userEmail)){
                                // Firebase 로그인 인증하러 가기
                                firebaseAuthWithGoogle(account);
                                break;
                            }
                        }

                        Toast.makeText(MainActivity.this, "Firebase 계정 존재하지 않음", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, JoinAcitivity.class);
                        intent.putExtra("useremail", userEmail);
                        intent.putExtra("tokenId", account.getIdToken());
                        startActivityForResult(intent, 1005);

                    } // onDataChange

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });


            }catch(ApiException e){
                e.printStackTrace();
            }// try...catch
        }
        else if(requestCode == 1005) {
            if(requestCode == RESULT_OK) {
                //TOTO 메인화면으로 이동
                //goMainActivity();
                finish();
            }
        }
        // if...else
    }// end onActivityResult


} // end
