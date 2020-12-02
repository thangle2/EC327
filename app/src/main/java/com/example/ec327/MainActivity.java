package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button buttonsplash;
    ImageView ivSplash;                                             //test commit 23
    Animation atg,textone,texttwo;
    int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test=1;
        test=1; //for home screen access

        //constructors

        buttonsplash=findViewById(R.id.buttonsplash);
        ivSplash=findViewById(R.id.logo);

        //fonts
        Typeface AR=Typeface.createFromAsset(getAssets(),"fonts/anmatic_regular.ttf");
        Typeface AB=Typeface.createFromAsset(getAssets(),"fonts/chunkfive_print.otf");
        atg= AnimationUtils.loadAnimation(this,R.anim.atg);
        textone= AnimationUtils.loadAnimation(this,R.anim.textone);
        texttwo= AnimationUtils.loadAnimation(this,R.anim.texttwo);

        ivSplash.startAnimation(atg);

        buttonsplash.startAnimation(texttwo);
        if(test==1) {
            buttonsplash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(MainActivity.this, Survey.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(a);
                }
            });
        }
        if(test==2){
            buttonsplash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent(MainActivity.this, Home.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(a);
                }
            });
        }




    }
}