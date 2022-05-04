package com.example.prisonerofpower;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="players";
    public static final String TABLE_CONTACTS="players";

    public static final String KEY_ID ="_id";
    public static final String KEY_NAME ="name";
    public static final String KEY_PASSWORD ="password";
    public static final String KEY_SCORE="score";
    public static final String KEY_LEVELS="levels";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void put(SQLiteDatabase sqLiteDatabase,String score,String name) {
        Log.d("mLog","UPDATE " + TABLE_CONTACTS+" SET " + KEY_SCORE +"="+score+" WHERE " + KEY_NAME+"="+name);
        //sqLiteDatabase.execSQL("UPDATE " + TABLE_CONTACTS+" SET " + KEY_SCORE +"="+score+" WHERE " + KEY_NAME+"="+name+";");
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_CONTACTS+"("+KEY_ID+" integer primary key,"+KEY_NAME+
           " text,"+KEY_PASSWORD+" text,"+KEY_SCORE+" text,"+KEY_LEVELS+" text"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }
}
