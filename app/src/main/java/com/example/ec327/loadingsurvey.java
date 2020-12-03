package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class loadingsurvey extends AppCompatActivity {
    TextView loadingsurveytext;
    Animation animPulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingsurvey);
        loadingsurveytext = findViewById(R.id.loadingsurveytext);
        animPulse = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.pulse);
        loadingsurveytext.startAnimation(animPulse);
        animPulse.setDuration(500);

        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent a = new Intent(loadingsurvey.this, surveryresultinformation.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }

        }, 9000L);


    }
}