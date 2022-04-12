package com.example.prisonerofpower;




import android.app.Activity;

import android.content.Intent;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;




public class MainActivity extends Activity {

boolean translate=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView2);

       Animation animScale = AnimationUtils.loadAnimation(this, R.anim.start_button);
        imageView.startAnimation(animScale);



    }



    public void startGame(View view){

    Intent intent = new Intent(this,StartGame.class);
    startActivity(intent);
    translate=false;
    }
    public void shop(View view) {

        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);

    }
    public static void stop(View view)
    {

        new MainActivity().methodForStop(view);


    }

    public void methodForStop(View view) {

       Intent intent = new Intent(MainActivity.this, StopGame.class);
        startActivity(intent);


    }


}