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

public class pastAdapter extends RecyclerView.Adapter<pastAdapter.MyViewHolder> {


    private List<pastdata> pastdataList;
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

    public pastAdapter(List<pastdata> pastdataList, Context mContext) {
        this.pastdataList = pastdataList;
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
        pastdata mPastdata = pastdataList.get(position);
        holder.txtUserName.setText(mPastdata.getUserName());
        holder.txtTripName.setText(mPastdata.getTripName());
        holder.txtTripType.setText(mPastdata.getTripType());
        Picasso.with(mContext).load(mPastdata.getProfileImg())
                .transform(new CircleTransform()).into(holder.profileImg);
       holder.tripImg.setImageResource(mPastdata.getTripImg());


   }


    @Override
    public int getItemCount() {
        return pastdataList.size();
    }

}
