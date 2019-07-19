package com.cho2.finalproject;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinAcitivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ImageView mImgID; //학생증 사진
    private EditText mEdtPhone; //전화번호
    private EditText mEdtName;  //학생 이름
    private String mUserEmail;
    private String mTokenId;

    public static final String STORAGE_DB_URL ="gs://swu2019-finalproject-team2.appspot.com"; // firebase database url
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance(STORAGE_DB_URL);
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();


    //사진이 저장되는 경로 - onActivityResult()로부터 받는 데이터
    private Uri mCaptureUri;
    //사진이 저장된 단말기상의 실제 경로
    public String mPhotoPath;
    //startActivityForResult() 에 넘겨주는 값, 이 값이 나중에 onActivityResult()로 돌아와서
    //내가 던진값인지를 구별할 때 사용하는 상수이다.
    public static final int REQUEST_IMAGE_CAPTURE = 200;

    MemberBean mMemberBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // userEmail 획득
        mUserEmail = getIntent().getStringExtra("useremail");
        mTokenId = getIntent().getStringExtra("tokenId");

        //카메라를 사용하기 위한 퍼미션을 요청한다.

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        }, 0);

        //할당
        mImgID=findViewById(R.id.imgID);
        mEdtPhone=findViewById(R.id.EdtPhone);
        mEdtName=findViewById(R.id.EdtName);

        //사진 클릭하면 사진 찍기
        mImgID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        // 제출버튼
        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(); // 스토리지와 데이터베이스에 업로드함
            }
        });
    }//end Oncreate

    private void upload() {

        if (mPhotoPath == null) {
            Toast.makeText(this, "사진을 찍어주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mEdtName.getText())){
            Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mEdtPhone.getText())){
            Toast.makeText(this, " 전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuthWithGoogle(mTokenId);// 파이어베이스에 회원가입 및 로그인함

    } // end upload()

    // Firebase 회원가입하면서 로그인까지 되는 것
    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Firebase 로그인 성공
                            Toast.makeText(getBaseContext(), "Firebase 로그인 성공", Toast.LENGTH_LONG).show();
                            Log.d(TAG, " >> Firebase 로그인 성공");

                            uploadMemberDB();
                        }
                        else{
                            // 로그인 실패
                            Toast.makeText(getBaseContext(), "Firebase 로그인 실패", Toast.LENGTH_LONG).show();
                            Log.e(TAG, " >> 인증 실패"+task.getException());
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    }
                });
    } // end firebaseAuthWithGoogle


    private void uploadMemberDB(){

        StorageReference storageRef = mFirebaseStorage.getReference();
        final StorageReference imageRef = storageRef.child("images/" + mCaptureUri.getLastPathSegment());

        UploadTask uploadTask = imageRef.putFile(mCaptureUri);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                //데이터 베이스 업로드를 호출한다.
                DatabaseReference dbRef=mFirebaseDatabase.getReference();
                MemberBean memberBean = new MemberBean();
                memberBean.userEmail = mUserEmail;
                memberBean.ImgIDuri = mCaptureUri.getLastPathSegment();
                memberBean.name = mEdtName.getText().toString();
                memberBean.Phonenum = mEdtPhone.getText().toString();
                memberBean.isAdmin=false;

                Log.e(TAG, "memberBean.userEmail" + memberBean.userEmail);

                String userUUID = InsertFirebase.getUserIdFromUUID(memberBean.userEmail);
                dbRef.child("members").child(userUUID).setValue(memberBean);
                Toast.makeText(JoinAcitivity.this, "학생증, 사진, 이름 등록완료", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
            }
        });

    } // end uploadMemberDB


    private void takePicture() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mCaptureUri = Uri.fromFile( getOutPutMediaFile() );
        } else {
            mCaptureUri = FileProvider.getUriForFile(this,
                    "com.cho2.finalproject", getOutPutMediaFile());
        }

        i.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureUri);

        //내가 원하는 액티비티로 이동하고, 그 액티비티가 종료되면 (finish되면)
        //다시금 나의 액티비티의 onActivityResult() 메서드가 호출되는 구조이다.
        //내가 어떤 데이터를 받고 싶을때 상대 액티비티를 호출해주고 그 액티비티에서
        //호출한 나의 액티비티로 데이터를 넘겨주는 구조이다. 이때 호출되는 메서드가
        //onActivityResult() 메서드 이다.
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

    }
    private File getOutPutMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "cameraDemo");
        if(!mediaStorageDir.exists()) {
            if(!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        mPhotoPath = file.getAbsolutePath();

        return file;
    }
    private void sendPicture() {
        Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath);
        Bitmap resizedBmp = getResizedBitmap(bitmap, 4, 100, 100);

        bitmap.recycle();

        //사진이 캡쳐되서 들어오면 뒤집어져 있다. 이 애를 다시 원상복구 시킨다.
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(mPhotoPath);
        } catch(Exception e) {
            e.printStackTrace();
        }
        int exifOrientation;
        int exifDegree;
        if(exif != null) {
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientToDegree(exifOrientation);
        } else {
            exifDegree = 0;
        }
        Bitmap rotatedBmp = roate(resizedBmp, exifDegree);
        mImgID.setImageBitmap( rotatedBmp );
        //줄어든 이미지를 다시 저장한다
        saveBitmapToFileCache(resizedBmp, mPhotoPath);

        Toast.makeText(this, "사진경로: " + mPhotoPath, Toast.LENGTH_LONG).show();
    }

    private void saveBitmapToFileCache(Bitmap bitmap, String strFilePath) {

        File fileCacheItem = new File(strFilePath);
        OutputStream out = null;

        try
        {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private int exifOrientToDegree(int exifOrientation) {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap roate(Bitmap bmp, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
                matrix, true);
    }

    //비트맵의 사이즈를 줄여준다.
    public static Bitmap getResizedBitmap(Bitmap srcBmp, int size, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap resized = Bitmap.createScaledBitmap(srcBmp, width, height, true);
        return resized;
    }

    public static Bitmap getResizedBitmap(Resources resources, int id, int size, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap src = BitmapFactory.decodeResource(resources, id, options);
        Bitmap resized = Bitmap.createScaledBitmap(src, width, height, true);
        return resized;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //카메라로부터 오는 데이터를 취득한다.
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_IMAGE_CAPTURE) {
                sendPicture();
            }
        }
    }

}
