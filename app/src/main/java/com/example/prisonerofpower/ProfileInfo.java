package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ProfileInfo extends AppCompatActivity {

    public static String Name,Pass,Score,Levels;
    public static float ScoreInt;
    TextView twName,twPass,twScore,twLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
    public void back(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void goToLeaderboards(View view)
    {
        Intent intent = new Intent(this,leaderboards.class);
        startActivity(intent);
    }
    public void LogOut(View view)
    {
        Profile.LOGGED=false;
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

}