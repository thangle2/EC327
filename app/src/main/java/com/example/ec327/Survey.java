package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Survey extends AppCompatActivity {
    TextView textsurvey;
    Button buttonsurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        textsurvey=findViewById(R.id.textsurvey);
        buttonsurvey=findViewById(R.id.buttonsurvey);
        Typeface PB=Typeface.createFromAsset(getAssets(),"fonts/poppins_bold.otf");
        textsurvey.setTypeface(PB);
        buttonsurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Financials  originaluser = new Financials();
                Intent a = new Intent(Survey.this, information.class);
                a.putExtra("userObject",originaluser);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(a);
            }
        });
    }
}