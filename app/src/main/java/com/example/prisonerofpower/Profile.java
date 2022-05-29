package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

public class Profile extends Activity implements View.OnClickListener{
    public static boolean LOGGED = false;
    EditText etName;
    EditText etPass;
    TextView etError;
    static DBHelper dbHelper;
    Button btnAdd,btnLog,btnDelete;
    public static SQLiteDatabase database;
    public static ContentValues contentValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        btnAdd=(Button) findViewById(R.id.button5) ;
        btnAdd.setOnClickListener(this);
        btnLog=(Button) findViewById(R.id.button4) ;
        btnLog.setOnClickListener(this);

        //btnRead=(Button) findViewById(R.id.button6) ;
        //btnRead.setOnClickListener(this);

        //btnDelete=(Button) findViewById(R.id.button3) ;
        //btnDelete.setOnClickListener(this);

        etName =(EditText) findViewById(R.id.editTextTextPersonName2);
        etPass=(EditText) findViewById(R.id.editTextTextPassword3);
        etError= (TextView) findViewById(R.id.textView4);
        dbHelper=new DBHelper(this);
    }
    @Override
    public void onClick(View v)
    {
        String name =etName.getText().toString();
        String pass =etPass.getText().toString();
        database = dbHelper.getWritableDatabase();
         contentValues = new ContentValues();
        switch (v.getId())
        {
            case R.id.button5:
                if(loggin(name,pass,0)) etError.setText("There is a profile with this name");
                else {
                    contentValues.put(DBHelper.KEY_NAME, name);
                    contentValues.put(DBHelper.KEY_PASSWORD, pass);
                    contentValues.put(String.valueOf(DBHelper.KEY_SCORE), 0);
                    contentValues.put(String.valueOf(DBHelper.KEY_LEVELS), 1);

                    database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                    LOGGED = true;
                    Log.d("mLog", "aa");
                    ProfileInfo.Name = name;
                    ProfileInfo.Pass = pass;
                    ProfileInfo.Score = 0;
                    ProfileInfo.Levels = 1;
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    //database.delete(DBHelper.TABLE_CONTACTS,null,null);
                }
                break;
            case R.id.button4:

                if(!loggin(name,pass,1)) etError.setText("Wrong name or password");
                else
                {
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                }
                break;
            /*case R.id.button6:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    int idIndex = cursor.getColumnIndex((DBHelper.KEY_ID));
                    int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
                    int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));
                    do{
                        Log.d("mLog","ID = "+ cursor.getInt(idIndex)+
                                ", name = " + cursor.getString(nameIndex)+
                                ", password = " + cursor.getString(passIndex));
                        addRow(cursor.getString(nameIndex),cursor.getString(passIndex));
                    } while(cursor.moveToNext());

                }
                else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;
            case R.id.button3:
                database.delete(DBHelper.TABLE_CONTACTS,null,null);
                break;*/
        }
        //dbHelper.close();
    }

public boolean loggin(String name, String pass, int i)
{

    Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,null,null,null,null,null,null);
    if(cursor.moveToFirst())
    {

        int nameIndex = cursor.getColumnIndex((DBHelper.KEY_NAME));
        int passIndex = cursor.getColumnIndex((DBHelper.KEY_PASSWORD));
        int scoreIndex = cursor.getColumnIndex(String.valueOf((DBHelper.KEY_SCORE)));
        int levelsIndex = cursor.getColumnIndex(String.valueOf((DBHelper.KEY_LEVELS)));

        do{

            if(name.equals(cursor.getString(nameIndex)))
            {
                Log.d("mLog",name);
                Log.d("mLog",cursor.getString(nameIndex));
                if(i==0) return true;

                if(pass.equals(cursor.getString(passIndex)))
                {
                    Log.d("mLog","gothere");
                    LOGGED=true;
                    ProfileInfo.Name=name;
                    ProfileInfo.Pass=pass;
                    ProfileInfo.Score=cursor.getFloat(scoreIndex);
                    ProfileInfo.Levels=cursor.getInt(levelsIndex);
                    return true;

                }
            }
        } while(cursor.moveToNext());

    }


    cursor.close();
   return false;
}

}