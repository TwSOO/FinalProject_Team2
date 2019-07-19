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

import androidx.annotation.NonNull;

import com.cho2.finalproject.MyPageActivity;
import com.cho2.finalproject.R;
import com.cho2.finalproject.bean.MemberBean;
import com.cho2.finalproject.bean.ReservationCompleteBean;
import com.cho2.finalproject.bean.TimeBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdminPageAdapter extends BaseAdapter {

    private Context mContext;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;
    private List<ReservationCompleteBean> mReservationList;


    public AdminPageAdapter(Context context, List<ReservationCompleteBean> reservationList) {
        mContext = context;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = mFirebaseDatabase.getReference();
        mReservationList = reservationList;
    }

    @Override
    public int getCount() {
        return mReservationList.size();
    }

    @Override
    public Object getItem(int i) {
        return mReservationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_admin_item, null);

        final ReservationCompleteBean rcBean = mReservationList.get(position);

         //xml파일을 맵핑
        TextView txtResBuilding=view.findViewById(R.id.txtResBuilding); //건물
        TextView txtTime = view.findViewById(R.id.txtResTime); //시간
        TextView txtResRoom = view.findViewById(R.id.txtResRoom); //강의실
        TextView txtDate=view.findViewById(R.id.txtResDate);
        Button btnCancel = view.findViewById(R.id.btnCancel);


        txtResBuilding.setText(rcBean.step1BuildName);
        txtTime.setText(rcBean.step2Time);
        txtResRoom.setText(rcBean.step3RoomName);
        txtDate.setText(rcBean.step2Day);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(position, timeBean);
            }

        });
        return view;
    }
    public void cancel(ReservationCompleteBean rcBean, final int position, final TimeBean timeBean, int timeindex){

        // 선택된 rcBean에 해당하는 Index값을 가지고 reservation List로 가서 true값으로 바꿈
        final int timeIndex = rcBean.timeIndex;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
        alertDialog.setMessage("시간: " + timeBean.timeTitle + "\n예약취소하시겠습니까?"); //가운데 정렬로 하고싶음.
        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                timeBean.isReservation = false;
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
                mMemberBean.reservationCompleteList.remove( timeIndex );


                // 로그인한 사용자에게서 예약데이터 삭제
                final String uuid = InsertFirebase.getUserIdFromUUID( FirebaseAuth.getInstance().getCurrentUser().getEmail() );
                //사용자 찾기
                mFirebaseDatabase.getReference().child("members").child(uuid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final MemberBean memberBean = dataSnapshot.getValue(MemberBean.class);

                        memberBean.reservationCompleteList.remove(position);

                        mFirebaseDatabase.getReference().child("members").child(uuid).setValue(memberBean);
                    } // onDataChange

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });


                dbRef.child("members").child(uuid).setValue(mMemberBean);

                Toast.makeText(mContext,"예약 완료", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(mContext, MyPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("아니오", null);
        alertDialog.create().show();
    }




}
