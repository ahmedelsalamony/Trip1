package com.example.itimobiletrack.trip1.home.upcomingtrips;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.itimobiletrack.trip1.DBHelper;
import com.example.itimobiletrack.trip1.R;
//import com.example.itimobiletrack.trip1.addnewtrip.AddTrip;
import com.example.itimobiletrack.trip1.mListView.CustomAdapter;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by ahmed on 2/14/2017.
 */

public class UpcomingFragment extends Fragment {


    //cashed data from db in this object>>>tripArrayList
    List<Trip> tripArrayList = new ArrayList<>();
    ListView mListView;
    Button btnsave, btndate, btntime;
    CustomAdapter mCustomAdapter;
    final boolean forupdate = true;

    TextView txtTripName, txtStartP, txtEndP, txtNotes;
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
    View v;
    Trip mTrip;
    DBHelper mydb;
    int flagStatus = 1;

    Button buttonSetAlarm;


    public UpcomingFragment() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.upcomingfragment, container, false);
        mydb = new DBHelper(getActivity());
        mListView = (ListView) v.findViewById(R.id.lst);
        mListView.setDivider(null);
        mListView.setDividerHeight(0);
        mCustomAdapter = new CustomAdapter(getActivity(), (ArrayList<Trip>) tripArrayList);
        this.getSpacecrafts();
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                displayDialog(false);
            }
        });

        return v;

    }


    public void displayDialog(boolean forupdate) {
        final Dialog d = new Dialog(getActivity());
        d.setTitle("Tripawy");
        d.setContentView(R.layout.activity_add_trip);
        txtTripName = (TextView) d.findViewById(R.id.input_tripname);
        ////for places
        txtStartP = (TextView) d.findViewById(R.id.input_startP);
        txtEndP = (TextView) d.findViewById(R.id.input_endP);
        map = (Button) d.findViewById(R.id.btnmaps);
        /////
        switchButton = (Switch) d.findViewById(R.id.switchButton);
        txtNotes = (TextView) d.findViewById(R.id.input_notes);
        rdOneDirection = (RadioButton) d.findViewById(R.id.rdonedirection);
        rdRound = (RadioButton) d.findViewById(R.id.rdround);
        btndate = (Button) d.findViewById(R.id.btndatedetail);
        btntime = (Button) d.findViewById(R.id.btntimedetail);
        btnsave = (Button) d.findViewById(R.id.btnsave);

        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    flagStatus = 1;
                } else {
                    flagStatus = 0;
                }
            }
        });


                ///////////////////
                txtStartP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag = false;
                        Intent intent = null;
                        try {
                            intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(getActivity());
                        } catch (GooglePlayServicesRepairableException e) {
                            e.printStackTrace();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                        startActivityForResult(intent, 1);
                    }
                });
        txtEndP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                Intent intent = null;
                try {
                    intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(getActivity());
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, 1);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtStartP.getText().toString().equals("") || txtEndP.getText().toString().equals("")) {


                } else {
                    Uri gmmIntentUri = Uri.parse("https://maps.google.ca/maps?saddr=" + startAddress.getAddress().toString() + "&daddr=" + endAddress.getAddress().toString());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    startActivity(mapIntent);
                }
            }
        });


        // getSpacecrafts();
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getActivity(), mDateSetListener, yr, month, day).show();

            }
        });

        //////////////////////////////////
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// to show time dialog

                new TimePickerDialog(
                        getActivity(), mTimeSetListener, hour, min, false).show();
            }
        });
        ///////////////////////////
        if (!forupdate) {
            btnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int round = 0;
                    int onedirection = 0;
                    if (rdRound.isChecked()) {
                        round = 1;
                    }
                    if (rdOneDirection.isChecked()) {
                        onedirection = 1;
                    }
                    save(txtTripName.getText().toString(), txtStartP.getText().toString(), txtEndP.getText().toString()
                            , txtNotes.getText().toString(), btndate.getText().toString(), btntime.getText().toString(), round, onedirection, flagStatus);

                    d.dismiss();
                }
            });

        } else {
            txtTripName.setText(mCustomAdapter.allDataForEdit().getTripName());
            txtStartP.setText(mCustomAdapter.allDataForEdit().getsPoint());
            txtEndP.setText(mCustomAdapter.allDataForEdit().getEpoint());
            txtNotes.setText(mCustomAdapter.allDataForEdit().getStrnotes());
            btndate.setText(mCustomAdapter.allDataForEdit().getStrdate());
            btntime.setText(mCustomAdapter.allDataForEdit().getStrtime());
            int round = mCustomAdapter.allDataForEdit().getRound();
            if (round == 1) {
                rdRound.setChecked(true);
            } else {
                rdRound.setChecked(false);
            }
            int one = mCustomAdapter.allDataForEdit().getOnedirection();
            if (one == 1) {
                rdOneDirection.setChecked(true);
            } else {
                rdOneDirection.setChecked(false);
            }
            switchButton.setChecked(true);


            btnsave.setOnClickListener(new View.OnClickListener() {
                int round;
                int onedirection;

                @Override
                public void onClick(View view) {

                    if (rdRound.isChecked()) {
                        round = 1;
                    } else {
                        round = 0;
                    }
                    if (rdOneDirection.isChecked()) {
                        onedirection = 1;
                    } else {
                        onedirection = 0;
                    }
                    update(txtTripName.getText().toString(), txtStartP.getText().toString(), txtEndP.getText().toString(),
                            txtNotes.getText().toString(), btndate.getText().toString(), btntime.getText().toString(),
                            round, onedirection, flagStatus);
                    d.dismiss();
                }
            });

        }

        d.show();

    }


    //SAVE
    private void save(String strTripName, String strStartP, String strEndP, String strNotes,
                      String strDate, String strTime, int round, int retrieveOneDirection, int status) {

       /* DBAdapter db=new DBAdapter(this);
        db.openDB();*/
        mydb = new DBHelper(getActivity());
        boolean saved = mydb.insertContact(strTripName, strStartP, strEndP, strNotes, strDate, strTime, round, retrieveOneDirection, status);
        if (saved) {

            getSpacecrafts();

        } else {
            Toast.makeText(getActivity(), "Unable To Save", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                if (flag == false) {
                    startAddress = PlaceAutocomplete.getPlace(getActivity(), data);
                    txtStartP.setText(startAddress.getAddress());
                } else {
                    endAddress = PlaceAutocomplete.getPlace(getActivity(), data);
                    txtEndP.setText(endAddress.getAddress());
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

    //RETRIEVE OR GETSPACECRAFTS
    private void getSpacecrafts() {
        tripArrayList.clear();//avoid duplication in listview items

        Cursor c = mydb.getAllCotacts1(1);
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

    //UPDATE OR EDIT
    private void update(String newName, String newStartP, String newEndP, String newNotes, String newDate, String newTime, int newRound, int newOne, int status) {
        //GET ID OF SPACECRAFT
        int id = mCustomAdapter.getSelectedItemId();
        //UPDATE IN DB
        boolean updated = mydb.updateContact(newName, newStartP, newEndP, newNotes, newDate, newTime, newRound, newOne, id, status);
        if (updated) {
            txtTripName.setText(newName);
            txtStartP.setText(newStartP);
            getSpacecrafts();
        } else {
            Toast.makeText(getActivity(), "Unable To Update", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete() {
        //GET ID
        int id = mCustomAdapter.getSelectedItemId();
        //DELETE FROM DB
        boolean deleted = mydb.deleteContact(id);
        if (deleted) {
            getSpacecrafts();
        } else {
            Toast.makeText(getActivity(), "Unable To Delete", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if (title == "New") {
            displayDialog(!forupdate);
        } else if (title == "Edit") {
            displayDialog(forupdate);
        } else if (title == "Delete") {
            delete();
        }
        return super.onContextItemSelected(item);
    }


    // methods that return date and time
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(
                DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            yr = year;
            month = monthOfYear;
            day = dayOfMonth;

            btndate.setText("" + (month + 1) + "/" + day + "/" + year);
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(
                TimePicker view, int hourOfDay, int minuteOfHour) {
            hour = hourOfDay;
            min = minuteOfHour;
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
            Date date = new Date(0, 0, 0, hour, min);
            String strDate = timeFormat.format(date);
            btntime.setText("" + strDate);

        }
    };



}
