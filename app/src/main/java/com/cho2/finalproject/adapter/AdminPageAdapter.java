package com.cho2.finalproject.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Contacts;
import android.text.TextUtils;
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
import com.cho2.finalproject.bean.ReservationBean;
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
    private ReservationBean mReservationBean;
    private MemberBean mMemberBean;
    private List<TimeBean> mTimeList;



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
                // 예약한 사용자의이메일
                final String email = rcBean.mEmail;

                dbRef.child("members").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            mMemberBean = snapshot.getValue(MemberBean.class);
                            if(TextUtils.equals(mMemberBean.userEmail, email)){
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if(mMemberBean != null){
                    cancel(rcBean, position);
                }

            }

        });
        return view;
    }
    public void cancel(final ReservationCompleteBean rcBean, final int position){

        // 선택된 rcBean에 해당하는 Index값을 가지고 reservation List로 가서 true값으로 바꿈
        final int timeIndex = rcBean.timeIndex;

        String time = rcBean.step2Time;

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
        alertDialog.setMessage("시간: " + time + "\n예약취소하시겠습니까?"); //가운데 정렬로 하고싶음.
        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // 로그인한 사용자에게서 예약데이터 삭제
                final String uuid = InsertFirebase.getUserIdFromUUID( rcBean.mEmail ); // 관리자에서는 getCurrentUser() 대신 예약한 사람 uuid 받아와야함
                mMemberBean.reservationCompleteList.remove(position);
                dbRef.child("members").child(uuid).setValue(mMemberBean);

                // 데이터베이스의 reservations 폴더에서 예약 데이터 변경
                dbRef.child("reservations").child(rcBean.step1BuildName).child(rcBean.step2Day).child(rcBean.step3RoomName).addListenerForSingleValueEvent (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ReservationBean reservationBean = dataSnapshot.getValue(ReservationBean.class);
                        List<TimeBean> timeBeanList = reservationBean.getTimeList();

                        TimeBean timeBean1 = timeBeanList.get(timeIndex);
                        timeBean1.isReservation = false;

                        timeBeanList.add(timeIndex, timeBean1);
                        reservationBean.setTImeList(timeBeanList);

                        dbRef.child(reservationBean.step1BuildName).child(reservationBean.step2Day).child(reservationBean.step3RoomName).setValue(reservationBean);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
