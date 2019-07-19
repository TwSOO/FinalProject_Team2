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
import com.cho2.finalproject.bean.ReservationBean;
import com.cho2.finalproject.bean.TimeBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyPageAdapter extends BaseAdapter {

    private Context mContext;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference dbRef;
    List<ReservationBean> mReservationList;


    public MyPageAdapter(Context context, List<ReservationBean> reservationList) {
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
        view = inflater.inflate(R.layout.view_resv_item, null);

        final ReservationBean reservationBean = mReservationList.get(position);

        // xml파일을 맵핑
//        TextView txtTime = view.findViewById(R.id.txtTime);
//        TextView txtReservation = view.findViewById(R.id.txtResvState);
//        Button btnReservation = view.findViewById(R.id.btnReserve);



        return view;
    }

}
