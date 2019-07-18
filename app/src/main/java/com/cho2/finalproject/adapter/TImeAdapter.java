package com.cho2.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cho2.finalproject.R;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.bean.TimeBean;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TImeAdapter extends BaseAdapter {

    private Context mContext;
    private ReservationBean mReservationBean;
    private List<TimeBean> mTimeList;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;



    public TImeAdapter(Context context, ReservationBean reservationBean) {
        mContext = context;
        mReservationBean = reservationBean;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
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
        }
        else{
            txtReservation.setText("예약가능");
        }
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservation(timeBean.timeTitle);
            }
        });



        return view;
    }


    private void reservation(String reservationTime){

        dbRef.child("reservations").child(mReservationBean.step1BuildName).child(mReservationBean.step2Day).child(mReservationBean.step3RoomName).child(reservationTime).setValue(mReservationBean.mEmail);
        Toast.makeText(mContext,"예약 완료", Toast.LENGTH_LONG).show();

    }

    //10시버튼
//        findViewById(R.id.btnRes10).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReservationActivity.this);
//                alertDialog.setTitle("예약확인"); //이것도 가운데로 정렬하고 싶음.
//                alertDialog.setMessage("시간:10:00~11:00\n예약하시겠습니까?"); //가운데 정렬로 하고싶음.
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        reservationBean.mReserveTime = "10:00";
//                        reservation(reservationBean);
//                        Intent intent = new Intent(ReservationActivity.this, MyPageActivity.class);
//                        intent.putExtra("reservation", reservationBean);
//                        startActivity(intent);
//                    }
//                });
//                alertDialog.setNeutralButton("취소", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) { }
//                });
//                alertDialog.show();
//            }
//        });

}
