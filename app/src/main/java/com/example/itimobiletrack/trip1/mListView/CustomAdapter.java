package com.example.itimobiletrack.trip1.mListView;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itimobiletrack.trip1.DialogActivity;
import com.example.itimobiletrack.trip1.R;
import com.example.itimobiletrack.trip1.home.upcomingtrips.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by ahmed on 2/24/2017.
 */

public class CustomAdapter extends BaseAdapter {

    LayoutInflater infalInflater;
    Trip mTrip;
    Context mContext;
    ArrayList<Trip> mArrayList;


    TextView txtDate, txtTime, txtTripName, txtStartP, txtEndP, txtNotes;
    RadioButton rdOneDirection, rdRound;
    Button btndate,btntime;

    Button buttonSetAlarm;

    ///////// objs for places
    Button map;
    ////flag for retreive data from google search api to its textview
    boolean flag = false;

    public CustomAdapter(Context mContext,ArrayList<Trip> mArrayList ) {
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
        final MyViewHolder holder=new MyViewHolder(view);
        holder.txtTripName.setText(mArrayList.get(position).getTripName());
        holder.txtFrom.setText(mArrayList.get(position).getsPoint());
        holder.txtTo.setText(mArrayList.get(position).getEpoint());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog d=new Dialog(mContext);
                d.setTitle("new dialog  listview");
                d.setContentView(R.layout.dialoglist);
                txtDate = (TextView) d.findViewById(R.id.input_date);
                txtTime = (TextView) d.findViewById(R.id.input_time);
                txtTripName = (TextView) d.findViewById(R.id.input_tripname);
                txtStartP = (TextView) d.findViewById(R.id.input_startP);
                txtEndP = (TextView) d.findViewById(R.id.input_endP);
                txtNotes = (TextView) d.findViewById(R.id.input_notes);
                rdOneDirection = (RadioButton) d.findViewById(R.id.rdonedirection);
                rdRound = (RadioButton) d.findViewById(R.id.rdround);
                ////for places
                txtStartP = (TextView) d.findViewById(R.id.input_startP);
                txtEndP = (TextView) d.findViewById(R.id.input_endP);

                btndate = (Button) d.findViewById(R.id.iddate);
                btntime = (Button) d.findViewById(R.id.idtime);
                buttonSetAlarm = (Button)d.findViewById(R.id.setalarm);
                buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(mContext, DialogActivity.AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,/*id of the trip*/ 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                        Date dd = null;
                        try {
                            dd=new SimpleDateFormat("dd/mm/yyyy hh:mm aa",
                                    Locale.getDefault()).parse(mTrip.getStrdate()+" "+ mTrip.getStrtime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
/*date.gettime()cal.getTimeInMillis() + 1000 * 10 */
                        am.set(AlarmManager.RTC_WAKEUP,dd.getTime(), pendingIntent);

                        Toast.makeText(mContext, dd.getTime()+" ", Toast.LENGTH_SHORT).show();
        /*am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);*/

                    }
                });

                txtTripName.setText(mArrayList.get(position).getTripName());
                txtStartP.setText(mArrayList.get(position).getsPoint());
                txtEndP.setText(mArrayList.get(position).getEpoint());
                txtNotes.setText(mArrayList.get(position).getStrnotes());
                int round=mArrayList.get(position).getRound();
                if (round==1){
                    rdRound.setChecked(true);
                }else {
                    rdOneDirection.setChecked(true);
                }
                int onedirection=mArrayList.get(position).getOnedirection();
                if (onedirection==1){
                    rdOneDirection.setChecked(true);
                }else {
                    rdOneDirection.setChecked(false);
                }
                btndate.setText(mArrayList.get(position).getStrdate());
                btntime.setText(mArrayList.get(position).getStrtime());

                txtTripName.setKeyListener(null);
                txtStartP.setKeyListener(null);
                txtEndP.setKeyListener(null);
                txtNotes.setKeyListener(null);
                rdRound.setKeyListener(null);
                rdOneDirection.setKeyListener(null);
                d.show();

               }
        });
        //get the current item position
        holder.setmMyLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
            mTrip =(Trip)getItem(position);
            }
        });

        return view;
    }



    //expose name and id
    public int getSelectedItemId(){
        return mTrip.getTripid();
    }

    public String getselectedItemName(){
        return mTrip.getTripName();
    }

    public Trip allDataForEdit(){
        return mTrip;
    }

}
