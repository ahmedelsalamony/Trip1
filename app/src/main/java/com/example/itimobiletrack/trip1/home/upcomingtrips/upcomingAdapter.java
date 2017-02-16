package com.example.itimobiletrack.trip1.home.upcomingtrips;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itimobiletrack.trip1.R;

import java.util.List;

/**
 * Created by ahmed on 2/15/2017.
 */

public class upcomingAdapter extends RecyclerView.Adapter<upcomingAdapter.MyViewHolder> {


    private List<upcomingdata> upcomingdataList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTripName, txtTripType;
        public ImageView  tripImg;


        public MyViewHolder(View view) {
            super(view);
            txtTripName = (TextView) view.findViewById(R.id.tripName);
            txtTripType = (TextView) view.findViewById(R.id.tripType);
            tripImg = (ImageView) view.findViewById(R.id.myImageView);
        }



     }

    public upcomingAdapter(List<upcomingdata> todaydataList, Context mContext) {
        this.upcomingdataList = todaydataList;
        this.mContext=mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todayrow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        upcomingdata mUpcoming = upcomingdataList.get(position);
        holder.txtTripName.setText(mUpcoming.getTripName());
        holder.txtTripType.setText(mUpcoming.getTripType());
       holder.tripImg.setImageResource(mUpcoming.getTripImg());


   }


    @Override
    public int getItemCount() {
        return upcomingdataList.size();
    }


}
