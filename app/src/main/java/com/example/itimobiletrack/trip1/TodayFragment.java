package com.example.itimobiletrack.trip1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/14/2017.
 */

public class TodayFragment extends Fragment {

    private List<todaydata> todaydataArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private todayAdapter mAdapter;
    View v;
    todaydata mTodaydata;
    public TodayFragment(){}

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

        mAdapter = new todayAdapter(todaydataArrayList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
        return v;
    }
    private void prepareMovieData() {
        mTodaydata= new todaydata(R.drawable.cairoimg,R.drawable.cairoimg,
                "ahmed elsalamony","alex trip","round trip");
        todaydataArrayList.add(mTodaydata);

        mTodaydata= new todaydata(R.drawable.cairoimg,R.drawable.cairoimg,
                "ahmed elsalamony","alex trip","round trip");
        todaydataArrayList.add(mTodaydata);

        mTodaydata= new todaydata(R.drawable.cairoimg,R.drawable.cairoimg,
                "ahmed elsalamony","alex trip","round trip");
        todaydataArrayList.add(mTodaydata);

        mTodaydata= new todaydata(R.drawable.cairoimg,R.drawable.cairoimg,
                "ahmed elsalamony","alex trip","round trip");
        todaydataArrayList.add(mTodaydata);



    }
    }
