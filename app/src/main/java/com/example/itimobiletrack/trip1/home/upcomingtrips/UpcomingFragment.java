package com.example.itimobiletrack.trip1.home.upcomingtrips;

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

public class UpcomingFragment extends Fragment {

    private List<upcomingdata> upcomingdataArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private upcomingAdapter mAdapter;
    View v;
    upcomingdata mUpcomingdata;
    public UpcomingFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.upcomingfragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        mAdapter = new upcomingAdapter(upcomingdataArrayList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        return v;}
    private void prepareMovieData() {
        mUpcomingdata= new upcomingdata(R.drawable.cairoimg,
                "alex trip","round trip");
        upcomingdataArrayList.add(mUpcomingdata);

        mUpcomingdata= new upcomingdata(R.drawable.cairoimg,
                "alex trip","round trip");
        upcomingdataArrayList.add(mUpcomingdata);

        mUpcomingdata= new upcomingdata(R.drawable.cairoimg,
                "alex trip","round trip");
        upcomingdataArrayList.add(mUpcomingdata);

        mUpcomingdata= new upcomingdata(R.drawable.cairoimg,
                "alex trip","round trip");
        upcomingdataArrayList.add(mUpcomingdata);

        mAdapter.notifyDataSetChanged();
    }
}
