package com.cho2.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cho2.finalproject.R;
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.bean.TimeBean;

import java.util.List;

public class TImeAdapter extends BaseAdapter {

    private Context mContext;
    private ReservationBean mReservationBean;
    private List<TimeBean> mTimeList;


    public TImeAdapter(Context context, ReservationBean reservationBean) {
        mContext = context;
        mReservationBean = reservationBean;
        mTimeList = mReservationBean.getTimeList();
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

        TimeBean timeBean = mTimeList.get(position);


        return view;
    }


//    private void reservation(ReservationBean reservationBean){
//
//        DatabaseReference dbRef = mFirebaseDatabase.getReference();
//
//        dbRef.child("reservations").child(reservationBean.mReserveBuilding).child(reservationBean.mReserveRoom+"호").child(reservationBean.makeDate()).child(reservationBean.mReserveTime).setValue(reservationBean.mEmail);
//        Toast.makeText(this,"예약 완료", Toast.LENGTH_LONG).show();
//
//    }

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
