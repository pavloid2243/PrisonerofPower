package com.example.prisonerofpower;

import android.app.Activity;
import android.content.Intent;
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
        
        Intent intent = new Intent(this, StopGame.class);
        startActivity(intent);

    }

}
