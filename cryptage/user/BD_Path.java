package com.sci.fergani.cryptage.cryptage.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fergani on 28/05/2017.
 */

public class BD_Path extends SQLiteOpenHelper {
    public static final String DBname = "data1.db";
    public static final String TAblename = "mypath";
    public static final String CL2 = "PATH";
    public static final String CL3 = "NameFile";
    public static final int VERSION = 1;


    public BD_Path(Context context) {
        super(context, DBname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("CREATE TABLE mypath(NAmeFile TEXT PRIMARY KEY , PATH TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS mypath");
        onCreate(sqLiteDatabase);
    }
    public boolean insertPATh(String PATH, String NameFile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CL2, PATH);
        contentValues.put(CL3, NameFile);
        long result = db.insert(TAblename, null, contentValues);

        if (result == -1)
            return false;
        else {
            return true;
        }
    }

    public String getInfo(String filename) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor get = db.rawQuery("Select PATH from mypath where NAmefile ="+filename,null);
        get.moveToFirst();
            String t0 = get.getString(1);
        return t0;
    }
}