package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileInfo extends AppCompatActivity {

    public static String Name,Pass,Score,Levels;
    TextView twName,twPass,twScore,twLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        twName= (TextView) findViewById(R.id.textView6);
        twPass= (TextView) findViewById(R.id.textView7);
        twName.setText(Name);
        twPass.setText(Pass);
        twScore= (TextView) findViewById(R.id.textView9);
        twLevels= (TextView) findViewById(R.id.textView11);
        twScore.setText(Score);
        twLevels.setText(Levels);

    }
}