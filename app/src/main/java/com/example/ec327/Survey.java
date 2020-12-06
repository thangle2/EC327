package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Survey extends AppCompatActivity {
    TypeWriter textsurvey;
    Button buttonsurvey;
    Animation to_botton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        textsurvey = findViewById(R.id.textsurvey);
        buttonsurvey = findViewById(R.id.buttonsurvey);
        to_botton = AnimationUtils.loadAnimation(this, R.anim.to_bottomlong);
        buttonsurvey.setVisibility(View.INVISIBLE);
        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonsurvey.setVisibility(View.VISIBLE);

            }
        }, 5500);
        textsurvey.setText("");
        textsurvey.setCharacterDelay(35);
        textsurvey.animatedText("This looks like your first time using Piggy Saver!\n In order to fully utilize this app, we need you to take\n a simple survey before starting.");
        Typeface PB = Typeface.createFromAsset(getAssets(), "fonts/poppins_bold.otf");
        textsurvey.setTypeface(PB);
        buttonsurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsurvey.startAnimation(to_botton);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Financials originaluser = new Financials();
                        Intent a = new Intent(Survey.this, information.class);
                        a.putExtra("userObject", originaluser);
                        a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(a);

                    }
                }, 1000);

            }
        });
    }
}