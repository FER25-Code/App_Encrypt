package com.sci.fergani.cryptage.cryptage.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.id;


/**
 * Created by ferga on 08/03/2017.
 */

public class DB_sqlite extends SQLiteOpenHelper {
    public static final String DBname = "data.db";
    public static final String TAblename = "mytable";
    public static final String CL1 = "ID";
    public static final String CL2 = "Opération";
    public static final String CL3 = "File";
    public static final String CL4 = "Date";
    public static final int VERSION = 1;


    public DB_sqlite(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("CREATE TABLE mytable (id INTEGER PRIMARY KEY , Opération TEXT,File TEXt,Date TEXT )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(sqLiteDatabase);

    }

    public boolean test() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM mytable";
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String s;
        s = cursor.getString(cursor.getColumnIndex("password"));

        if (s == "") {
            return false;
        } else {

            return true;
        }

    }


    public boolean insertData(String Opération, String File, String Date) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(CL1, id);
        contentValues.put(CL2, Opération);
        contentValues.put(CL3, File);
        contentValues.put(CL4, Date);
        long result = db.insert(TAblename, null, contentValues);

        if (result == -1)
            return false;
        else {

            return true;
        }
    }

    public boolean UpDate(Object o, int id, String Opération, String File, String Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CL1, id);
        contentValues.put(CL2, Opération);
        contentValues.put(CL3, File);
        contentValues.put(CL4, Date);
        long result = db.update(TAblename, contentValues, "ID= ?", null);

        if (result == -1)
            return false;
        else {

            return true;
        }
    }

    public ArrayList getInfo() {
        ArrayList info = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor get = db.rawQuery("Select * from mytable", null);
        get.moveToFirst();
        while (get.isAfterLast() == false) {
            String t0 = get.getString(0);
            String t1 = get.getString(1);
            String t2 = get.getString(2);
            String t3 = get.getString(3);
            info.add(t0 + "-" + t1 + " " + t2 + " " + t3);
            get.moveToNext();
        }
        return info;
    }


    public long delete(int  id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where= CL1 +" = "+ id ;
        long i = db.delete(TAblename,where,null);
        Log.e("delete",where+" i = " + i);
        return i ;
    }
    public long deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.delete(TAblename,null,null);
        Log.e("delete"," i = " + i);
        return i ;
    }

}