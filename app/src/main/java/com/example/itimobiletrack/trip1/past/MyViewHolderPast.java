package com.example.itimobiletrack.trip1.past;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.itimobiletrack.trip1.R;
import com.example.itimobiletrack.trip1.mListView.MyLongClickListener;

/**
 * Created by ahmed on 2/24/2017.
 */

public class MyViewHolderPast  {
public TextView txtTripName,txtFrom,txtTo;
public MyLongClickListener mMyLongClickListener;

    public MyViewHolderPast(View view) {
        txtTripName=(TextView)view.findViewById(R.id.txtTest);
        txtFrom=(TextView)view.findViewById(R.id.from);
        txtTo=(TextView)view.findViewById(R.id.to);

    }

}
