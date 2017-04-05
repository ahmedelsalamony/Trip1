package com.example.itimobiletrack.trip1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ahmed on 2/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    public static final String DATABASE_NAME = "MyDBName11.db";
    private String Table_Trips = "Trips";

    public static final String KEY_ID_TODO = "ID";
    public static final String KEY_TRIPNAME = "TRIPNAME";
    public static final String KEY_startP = "SPOINT";
    public static final String KEY_endP = "EPOINT";
    public static final String KEY_TRIPDATE = "TRIPDATE";
    public static final String KEY_TRIPTIME = "TRIPTIME";
    public static final String KEY_ROUND = "ROUND";
    public static final String KEY_ONEDIRECTION = "ONEDIRECTION";
    public static final String KEY_Notes="notes";
    public static final String KEY_STATUS="status";
    SQLiteDatabase db;
    //remove imaeg item from data base because i will use picasso to cache the image


    /////////// will change datatype for date&time
    private String create_table = "CREATE TABLE IF NOT EXISTS " + Table_Trips + "(" + KEY_ID_TODO +
            "  INTEGER PRIMARY KEY autoincrement," + KEY_TRIPNAME + "  VARCHAR," + KEY_startP + "  VARCHAR," + KEY_endP +
            "  VARCHAR," + KEY_Notes + "  VARCHAR," +
            KEY_TRIPDATE + "  VARCHAR," + KEY_TRIPTIME + "  VARCHAR," + KEY_ROUND + "  INTEGER , " + KEY_ONEDIRECTION
                + "  INTEGER , " + KEY_STATUS + " INTEGER " + ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact(String tripName,String startP, String endP,String notes, String tripDate, String tripTime, int round, int  oneDirection,int status)
    {
       db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(KEY_ID_TODO,tripid);
        contentValues.put(KEY_TRIPNAME, tripName);
        contentValues.put(KEY_startP, startP);
        contentValues.put(KEY_endP, endP);
        contentValues.put(KEY_Notes,notes);
        contentValues.put(KEY_TRIPDATE, tripDate);
        contentValues.put(KEY_TRIPTIME, tripTime);
        contentValues.put(KEY_ROUND, round);
        contentValues.put(KEY_ONEDIRECTION, oneDirection);
        contentValues.put(KEY_STATUS,status);
        //recently added KEY_ID_TODO
        long result=db.insert(Table_Trips, KEY_ID_TODO, contentValues);
        if (result>0){
            return true;
        }
        return false;
    }

    //cursor is interface
    // it contain the result set returned by a database query
    //retrieve data form db
    public Cursor getAllCotacts1(int status) {
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Trips +" where " +KEY_STATUS +" = "+ status, null);
        return res;
    }

    //it update values of db of particular trip
    public boolean updateContact( String tripName,String startP, String endP,String notes,String tripDate, String tripTime, int round, int oneDirection,Integer id,int status){
       db = this.getWritableDatabase();
        //ContentValues >> this class extends object and implements parcelable
        //it contain set of values send it as a param
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRIPNAME, tripName);
        contentValues.put(KEY_startP, startP);
        contentValues.put(KEY_endP, endP);
        contentValues.put(KEY_Notes,notes);
        contentValues.put(KEY_TRIPDATE, tripDate);
        contentValues.put(KEY_TRIPTIME, tripTime);
        contentValues.put(KEY_ROUND, round);
        contentValues.put(KEY_ONEDIRECTION, oneDirection);
        contentValues.put(KEY_STATUS,status);
        db.update(Table_Trips, contentValues, KEY_ID_TODO + " =? ", new String[]{Integer.toString(id)});
        return true;
    }


    //---deletes a particular trip---
    public boolean deleteContact(int rowId){
        db = this.getWritableDatabase();
        Log.v("rowid", rowId + "@@@@@@@@@@@@@@@@@@@@@");
       int result= db.delete(Table_Trips, KEY_ID_TODO + " =?" , new String[]{String.valueOf(rowId)}) ;
        if (result>0){
            return true;
        }
        return false;
    }


    public Cursor getData(int id) {
      db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }

    //return id of trip name
    public Cursor getLastId() {
         db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select "+KEY_ID_TODO + " from "+Table_Trips, null);
        return res;
    }

    //this method return the number of raws in db
    public int numberOfRows() {
      db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Table_Trips);
        return numRows;
    }



    public ArrayList getAllCotacts() {
        ArrayList array_list = new ArrayList();
         db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Trips, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(KEY_TRIPNAME)));
            array_list.add(res.getString(res.getColumnIndex(KEY_startP)));
            res.moveToNext();
        }
        return array_list;
    }


}
