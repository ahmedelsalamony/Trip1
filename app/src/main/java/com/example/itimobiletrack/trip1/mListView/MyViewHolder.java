package com.example.itimobiletrack.trip1.mListView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.itimobiletrack.trip1.R;

/**
 * Created by ahmed on 2/24/2017.
 */

public class MyViewHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener{
public TextView txtTripName,txtFrom,txtTo;
public MyLongClickListener mMyLongClickListener;

    public MyViewHolder(View view) {
        txtTripName=(TextView)view.findViewById(R.id.txtTest);
        txtFrom=(TextView)view.findViewById(R.id.from);
        txtTo=(TextView)view.findViewById(R.id.to);
        view.setOnLongClickListener(this);
        view.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        this.mMyLongClickListener.onItemLongClick();

        return false;
    }

    public void setmMyLongClickListener(MyLongClickListener mMyLongClickListener){
        this.mMyLongClickListener=mMyLongClickListener;

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Action :: ");
        contextMenu.add(0,0,0,"New");
        contextMenu.add(0,1,0,"Edit");
        contextMenu.add(0,2,0,"Delete");
    }
}
