package com.example.prisonerofpower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class Shop extends Activity {

    public static int skin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop);



    }
    public void second(View view)
    {
        new Shop().change(1);
    }
    public void first(View view)
    {
        new Shop().change(0);
    }
    public void goBack(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void  change(int i) {

        skin =i;
    }

}
