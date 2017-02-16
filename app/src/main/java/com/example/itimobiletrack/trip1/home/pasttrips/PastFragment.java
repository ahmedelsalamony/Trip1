package com.example.itimobiletrack.trip1.home.pasttrips;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itimobiletrack.trip1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/14/2017.
 */

public class PastFragment extends Fragment {

    private List<pastdata> pastdataArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private pastAdapter mAdapter;
    View v;
    pastdata mTodaydata;
    public PastFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.todayfragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        mAdapter = new pastAdapter(pastdataArrayList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        return v;  }
    private void prepareMovieData() {
        mTodaydata= new pastdata(R.drawable.cairoimg,
                "alex trip","round trip");
        pastdataArrayList.add(mTodaydata);

        mTodaydata= new pastdata(R.drawable.cairoimg,
                "alex trip","round trip");
        pastdataArrayList.add(mTodaydata);

        mTodaydata= new pastdata(R.drawable.cairoimg,
                "alex trip","round trip");
        pastdataArrayList.add(mTodaydata);


        mAdapter.notifyDataSetChanged();
    }
}
