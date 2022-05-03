package com.example.prisonerofpower;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import androidx.annotation.Nullable;


public class StartGame extends Activity {
    GameView gameView;
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        gameView=new GameView(this);
        setContentView(gameView);
        GameView.lengthLevel = 1000;
    }

}
