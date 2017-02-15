package com.example.itimobiletrack.trip1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahmed on 2/15/2017.
 */

public class upcomingAdapter extends RecyclerView.Adapter<upcomingAdapter.MyViewHolder> {


    private List<upcomingdata> upcomingdataList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtUserName, txtTripName, txtTripType;
        public ImageView profileImg, tripImg;


        public MyViewHolder(View view) {
            super(view);
            txtUserName = (TextView) view.findViewById(R.id.userName);
            txtTripName = (TextView) view.findViewById(R.id.tripName);
            txtTripType = (TextView) view.findViewById(R.id.tripType);
            profileImg = (ImageView) view.findViewById(R.id.profileImg);
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
        holder.txtUserName.setText(mUpcoming.getUserName());
        holder.txtTripName.setText(mUpcoming.getTripName());
        holder.txtTripType.setText(mUpcoming.getTripType());
        Picasso.with(mContext).load(mUpcoming.getProfileImg())
                .transform(new CircleTransform()).into(holder.profileImg);
       holder.tripImg.setImageResource(mUpcoming.getTripImg());


   }


    @Override
    public int getItemCount() {
        return upcomingdataList.size();
    }

}
