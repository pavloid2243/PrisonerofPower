package com.example.prisonerofpower;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import androidx.annotation.Nullable;


public class StartGame extends Activity {
    GameView gameView;
 public static int lenght;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView = new GameView(this);
        setContentView(gameView);
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
        GameView.lengthLevel = lenght;
        MainActivity.nextLevel = false;

    }
}





