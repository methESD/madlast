package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DB_NAME = "Appoinments";
    private static String TABLE_NAME = "Appoinments";

    //COLUM NAMES
    private static final String APPOINMENTID = "id";
    private static final String BEAUTYNAME = "beautyname";
    private static final String CUSTOMERNAME = "customername";
    private static final String PHONENUMBER = "phonenumber";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " " +
                "("
                +APPOINMENTID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +BEAUTYNAME+ " TEXT,"
                +CUSTOMERNAME+ " TEXT,"
                +PHONENUMBER+ " INTEGER,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT"+

                ");";

        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //DROP IF EXIST
        db.execSQL(DROP_TABLE_QUERY);
        //CREATE AGAIN
        onCreate(db);
    }


    public void addAppoinment(Appoinment appoinment) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BEAUTYNAME, appoinment.getBeautyName());
        contentValues.put(CUSTOMERNAME, appoinment.getCusName());
        contentValues.put(PHONENUMBER, appoinment.getPhone());
        contentValues.put(STARTED, appoinment.getStarted());
        contentValues.put(FINISHED, appoinment.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //close
        sqLiteDatabase.close();

    }

    // Update a single Appoinmets
    public int updateAppoinment(Appoinment appoinment){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(BEAUTYNAME,appoinment.getBeautyName());
        contentValues.put(CUSTOMERNAME, appoinment.getCusName());
        contentValues.put(PHONENUMBER, appoinment.getPhone());
        contentValues.put(STARTED,appoinment.getStarted());
        contentValues.put(FINISHED,appoinment.getFinished());

        int status = db.update(TABLE_NAME,contentValues,APPOINMENTID +" =?",
                new String[]{String.valueOf(appoinment.getAppoinmentID())});

        db.close();
        return status;
    }
    //delete

    public void deleteAppoinmenet(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"APPOINMENTID =?",
                new String[]{String.valueOf(id)});
        db.close();

    }

    public  int countAppoinment(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " +TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();

    }


//    public Cursor getAllData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT * FROM" +TABLE_NAME,null);
//        return res;
//    }

   public List<Appoinment> getAllAppoinment() {

       List<Appoinment> appoinments = new ArrayList();
       SQLiteDatabase db = getReadableDatabase();
       String query = "SELECT * FROM " + TABLE_NAME;

       Cursor cursor = db.rawQuery(query, null);
       if (cursor.moveToFirst()) {
           do {
               Appoinment appoinment = new Appoinment();

               appoinment.setAppoinmentID(cursor.getInt(0));
               appoinment.setBeautyName(cursor.getString(1));
               appoinment.setCusName(cursor.getString(2));
               appoinment.setPhone(cursor.getString(3));
               appoinment.setStarted(cursor.getLong(4));
               appoinment.setFinished(cursor.getLong(5));

               appoinments.add(appoinment);
           }
           while (cursor.moveToNext());
       }
            return appoinments;
       }


    // Get a single appoinment
    public  Appoinment getSingleAppoinment(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{APPOINMENTID,BEAUTYNAME,CUSTOMERNAME,PHONENUMBER,STARTED, FINISHED},
                APPOINMENTID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null,null);

        Appoinment appoinment;
        if(cursor != null){
            cursor.moveToFirst();
            appoinment = new Appoinment(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getLong(4),
                    cursor.getLong(5)
            );
            return appoinment;
        }
        return null;
    }



}
