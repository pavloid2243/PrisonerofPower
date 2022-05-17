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

        if(Profile.LOGGED) {
            Intent intent = new Intent(this, StartGame.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }

    }
    public void shop(View view) {

        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);

    }
    public void profile(View view) {
        if(Profile.LOGGED==true)
        {
            Intent intent = new Intent(this, ProfileInfo.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onRestart() {

        super.onRestart();
        Intent intent = new Intent(this, StopGame.class);
        startActivity(intent);
        finish();
    }
}