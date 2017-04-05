package com.example.itimobiletrack.trip1.past;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.itimobiletrack.trip1.R;
import com.example.itimobiletrack.trip1.home.upcomingtrips.Trip;

import java.util.ArrayList;


/**
 * Created by ahmed on 2/24/2017.
 */

public class PastCustomAdapter extends BaseAdapter {

    LayoutInflater infalInflater;
    Context mContext;
    ArrayList<Trip> mArrayList;


    public PastCustomAdapter(Context mContext, ArrayList<Trip> mArrayList ) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (infalInflater==null){
            infalInflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        }
        if (view==null){
            view=infalInflater.inflate(R.layout.listrow,viewGroup,false);
        }
        //Bind data
        final MyViewHolderPast holder=new MyViewHolderPast(view);
        holder.txtTripName.setText(mArrayList.get(position).getTripName());
        holder.txtFrom.setText(mArrayList.get(position).getsPoint());
        holder.txtTo.setText(mArrayList.get(position).getEpoint());
        return view;
    }

}
