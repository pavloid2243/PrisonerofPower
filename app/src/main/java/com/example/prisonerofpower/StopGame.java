package com.example.prisonerofpower;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;


public class StopGame extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_game);
    }
    public static void stop(Context c)
    {

        new StopGame().methodForStop();


    }

    public void methodForStop() {
         Context c = getBaseContext();
        Intent intent = new Intent(c, StopGame.class);
        startActivity(intent);

    }
}