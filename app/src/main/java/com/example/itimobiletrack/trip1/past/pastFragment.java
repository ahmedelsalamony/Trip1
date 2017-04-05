package com.example.itimobiletrack.trip1.past;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.itimobiletrack.trip1.DBHelper;
import com.example.itimobiletrack.trip1.R;
import com.example.itimobiletrack.trip1.home.upcomingtrips.Trip;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.List;

//import com.example.itimobiletrack.trip1.addnewtrip.AddTrip;

/**
 * Created by ahmed on 2/14/2017.
 */

public class pastFragment extends Fragment {


    //cashed data from db in this object>>>tripArrayList
    List<Trip> tripArrayList = new ArrayList<>();
    ListView mListView;
    EditText mEditText;
    Button btnsave, btndate, btntime;
    PastCustomAdapter mCustomAdapter;
    final boolean forupdate = true;

    TextView  txtTripName, txtStartP, txtEndP, txtNotes;
    String strDate, strTime, strStartP, strEndP, strNotes;
    RadioButton rdOneDirection, rdRound;
    int retrieveOneDirection, retrieveRound;
    Switch switchButton;
    ///////// objs for places
    Button map;
    Place startAddress;
    Place endAddress;
    ////flag for retreive data from google search api to its textview
    boolean flag = false;
    int hour, min;
    int yr, month, day;
    private RecyclerView recyclerView;
    View v;
    Trip mTrip;
    DBHelper mydb;
    int flagStatus =1;

    public pastFragment() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.pastfragment, container, false);
        mydb = new DBHelper(getActivity());
        mListView = (ListView) v.findViewById(R.id.lst);
        mListView.setDivider(null);
        mListView.setDividerHeight(0);
        mCustomAdapter = new PastCustomAdapter(getActivity(), (ArrayList<Trip>) tripArrayList);
        this.getSpacecrafts();


        return v;

    }

    //RETRIEVE OR GETSPACECRAFTS
    private void getSpacecrafts() {
        tripArrayList.clear();//avoid duplication in listview items
        Cursor c = mydb.getAllCotacts1(0);
        mTrip = null;
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            strStartP = c.getString(2);
            strEndP = c.getString(3);
            strNotes = c.getString(4);
            strDate = c.getString(5);
            strTime = c.getString(6);
            retrieveRound = c.getInt(7);
            retrieveOneDirection = c.getInt(8);

            mTrip = new Trip();
            mTrip.setTripid(id);
            mTrip.setTripName(name);
            mTrip.setsPoint(strStartP);
            mTrip.setEpoint(strEndP);
            mTrip.setStrnotes(strNotes);
            mTrip.setStrdate(strDate);
            mTrip.setStrtime(strTime);
            mTrip.setRound(retrieveRound);
            mTrip.setOnedirection(retrieveOneDirection);

            tripArrayList.add(mTrip);
        }

        mCustomAdapter.notifyDataSetChanged();
        mListView.setAdapter(mCustomAdapter);
    }



}
