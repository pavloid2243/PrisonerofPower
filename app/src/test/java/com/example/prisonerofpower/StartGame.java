package com.example.prisonerofpower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class StartGame extends Activity {
    GameView gameView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        gameView=new GameView(this );
        setContentView(gameView);
    }
    public static void stop()
    {

        new StartGame().methodForStop();


    }

    public void methodForStop() {

        Intent intent = new Intent(this, StopGame.class);
        startActivity(intent);

    }


}
