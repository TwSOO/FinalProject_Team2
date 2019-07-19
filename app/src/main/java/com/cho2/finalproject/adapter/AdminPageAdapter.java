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

import com.cho2.finalproject.MyPageActivity;
import com.cho2.finalproject.R;
import com.cho2.finalproject.bean.ReservationCompleteBean;
import com.cho2.finalproject.bean.TimeBean;
import com.cho2.finalproject.firebase.InsertFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resCancelConfirm(position,rcBean);
//            }
//        });
//
//    }
//
//    private void resCancelConfirm(final int position, final ReservationCompleteBean rcBean, final TimeBean timeBean){
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//        alertDialog.setTitle("예약취소");
//        alertDialog.setMessage("일자: " + rcBean.step2Day+ "\n시간: "+rcBean.step2Time+"\n취소하시겠습니까?");
//        alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                timeBean.isReservation = false;
//                rcBean.mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
//                try {
//                    //시작시간
//                    rcBean.step2Time = sdf.parse("일자:"+rcBean.step2Day + "\n " + "시간:"+).getTime();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //기존 item 교체
//                mReservationBean.getTimeList().set(position, timeBean);
//
//                //예약 DB update
//                dbRef.child("reservations").child(mReservationBean.step1BuildName).child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).setValue(mReservationBean);
//
//                //사용자 DB update
//                if( mMemberBean.reservationCompleteList == null) {
//                    mMemberBean.reservationCompleteList = new ArrayList<>();
//                }
//                ReservationCompleteBean rcBean = new ReservationCompleteBean();
//                rcBean.step1BuildName = mReservationBean.step1BuildName;
//                rcBean.step2Day = mReservationBean.step2Day;
//                rcBean.step2Time = timeBean.timeTitle;
//                rcBean.step3RoomName = mReservationBean.step3RoomName;
//                rcBean.timeIndex = position;
//                mMemberBean.reservationCompleteList.add( rcBean );
//                String uuid = InsertFirebase.getUserIdFromUUID(mMemberBean.userEmail);
//                dbRef.child("members").child(uuid).setValue(mMemberBean);
//
//                Toast.makeText(mContext,"예약 완료", Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(mContext, MyPageActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                mContext.startActivity(intent);
//            }
//        });
//        alertDialog.setNegativeButton("아니오", null);
//        alertDialog.create().show();
        return view;

    }




}
