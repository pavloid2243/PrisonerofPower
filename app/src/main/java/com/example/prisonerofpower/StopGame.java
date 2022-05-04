package com.example.prisonerofpower;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;


public class StopGame extends Activity {

    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stop_game);


        Cursor cursor = Profile.database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor.moveToFirst())
        {
            int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
            int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));
            int scoreIndex = cursor.getColumnIndex((DBHelper.KEY_SCORE));
            int levelsIndex = cursor.getColumnIndex((DBHelper.KEY_LEVELS));
            ContentValues contentValues = new ContentValues();
            do{
                //leaderboards.addRowStage(cursor.getString(nameIndex),cursor.getString(passIndex),cursor.getString(scoreIndex),cursor.getString(levelsIndex));
                if(ProfileInfo.Name.equals(cursor.getString(nameIndex)))
                { Log.d("mLog","YESss ");
                    String where= DBHelper.KEY_NAME+"="+ProfileInfo.Name;
                    if(ProfileInfo.Pass.equals(cursor.getString(passIndex)))
                    {
                        /*contentValues.put(DBHelper.KEY_NAME,ProfileInfo.Name);
                        contentValues.put(DBHelper.KEY_PASSWORD,ProfileInfo.Pass);
                        contentValues.put(DBHelper.KEY_SCORE,ProfileInfo.Score);
                        contentValues.put(DBHelper.KEY_LEVELS,ProfileInfo.Levels);*/
                        Profile.database.insert(DBHelper.TABLE_CONTACTS,null,contentValues);
                        Profile.database.update(DBHelper.TABLE_CONTACTS, contentValues, where, null);
                       // DBHelper.put(Profile.database,ProfileInfo.Score,cursor.getString(nameIndex));
                        Log.d("mLog",ProfileInfo.Score);
                    }
                }
            } while(cursor.moveToNext());

        }
        else
            Log.d("mLog","0 rows");


        cursor.close();




    }
    public void gotoMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}