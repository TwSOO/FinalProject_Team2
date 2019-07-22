package com.cho2.finalproject.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cho2.finalproject.AdminActivity;
import com.cho2.finalproject.MyPageActivity;
import com.cho2.finalproject.R;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.bean.ReservationCompleteBean;
import com.cho2.finalproject.bean.TimeBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TImeAdapter extends BaseAdapter {

    private Context mContext;
    private ReservationBean mReservationBean;
    private MemberBean mMemberBean;
    private List<TimeBean> mTimeList;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;

    public TImeAdapter(Context context, ReservationBean reservationBean, MemberBean memberBean) {
        mContext = context;
        mReservationBean = reservationBean;
        mMemberBean = memberBean;
        mTimeList = mReservationBean.getTimeList();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
    }

    @Override
    public int getCount() {
        return mTimeList.size();
    }

    @Override
    public Object getItem(int i) {
        return mTimeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_resv, null);

        final TimeBean timeBean = mTimeList.get(position);

        // xml파일을 맵핑
        TextView txtTime = view.findViewById(R.id.txtTime);
        TextView txtReservation = view.findViewById(R.id.txtResvState);
        Button btnReservation = view.findViewById(R.id.btnReserve);

        // 아이템 정보 뷰에 담기 + 버튼 리스너 설정
        txtTime.setText(timeBean.timeTitle);
        if(timeBean.isReservation){
            txtReservation.setText("예약완료");
            btnReservation.setVisibility(View.INVISIBLE);
        }
        else{
            txtReservation.setText("예약가능");
            btnReservation.setVisibility(View.VISIBLE);
        }
        //예약하기 버튼 클릭
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reservation(position, timeBean);

            }
        });

        return view;
    }


    private void reservation(final int position, final TimeBean timeBean){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
        alertDialog.setMessage("시간: " + timeBean.timeTitle + "\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                timeBean.isReservation = true;
                timeBean.userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
                try {
                    //시작시간
                    timeBean.startMilliTime = sdf.parse(mReservationBean.step2Day + " " + timeBean.timeTitle).getTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //기존 item 교체
                mReservationBean.getTimeList().set(position, timeBean);

                //예약 DB update
                dbRef.child("reservations").child(mReservationBean.step1BuildName).child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).setValue(mReservationBean);

                //사용자 DB update
                if( mMemberBean.reservationCompleteList == null) {
                    mMemberBean.reservationCompleteList = new ArrayList<>();
                }
                ReservationCompleteBean rcBean = new ReservationCompleteBean();
                rcBean.step1BuildName = mReservationBean.step1BuildName;
                rcBean.step2Day = mReservationBean.step2Day;
                rcBean.step2Time = timeBean.timeTitle;
                rcBean.step3RoomName = mReservationBean.step3RoomName;
                rcBean.timeIndex = position;
                rcBean.mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                mMemberBean.reservationCompleteList.add( rcBean );
                String uuid = InsertFirebase.getUserIdFromUUID(mMemberBean.userEmail);
                dbRef.child("members").child(uuid).setValue(mMemberBean);

                Toast.makeText(mContext,"예약 완료", Toast.LENGTH_LONG).show();

                Intent intent;
                if(mMemberBean.isAdmin){
                    intent = new Intent(mContext, AdminActivity.class);
                }else{
                    intent = new Intent(mContext, MyPageActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("아니오", null);
        alertDialog.create().show();

    } // end reservation

}
