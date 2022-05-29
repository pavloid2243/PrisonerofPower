package com.example.prisonerofpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class wining extends AppCompatActivity {

    public static int lenght=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wining);
        ProfileInfo.Levels++;
        switch (ProfileInfo.Levels)
        {
            case 1:
                lenght=100;
                break;
            case 2:lenght=300;
                break;
            case 3: lenght=500;
                break;
            case 4: lenght=1000;
                break;
        }
        StopGame.updatetable();
    }
    public void gotoMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}