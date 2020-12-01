package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Type;


public class surveryresultinformation extends AppCompatActivity {
    Button donotknowinformationsurveyresult,oinkoink;
    EditText editsavings;
    Animation longrew,longshot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveryresultinformation);
        final TypeWriter textsurveryresultinformation=(TypeWriter) findViewById(R.id.textsurveryresultinformation);
        donotknowinformationsurveyresult=findViewById(R.id.donotknowinformationsurveyresult);
        longrew= AnimationUtils.loadAnimation(this,R.anim.longrew);
        longshot=AnimationUtils.loadAnimation(this,R.anim.longshot);
        editsavings=findViewById(R.id.editsavings);
        oinkoink=findViewById(R.id.oinkoink);
        Intent i=getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        String savedup=howmuch(orginaluser.getAge());
        String result= "Hello "+ orginaluser.getFirstName()+ "!\n"+
                "According to your age ("+ Integer.toString(orginaluser.getAge())+")\n"+"You should already have saved:\n"+ savedup+
                "\n\nHow much do you have saved?\nPress the pig nose to submit";
        textsurveryresultinformation.setText("");
        textsurveryresultinformation.setCharacterDelay(50);
        textsurveryresultinformation.animatedText(result);
        donotknowinformationsurveyresult.startAnimation(longrew);
        editsavings.startAnimation(longshot);
        donotknowinformationsurveyresult.setOnClickListener(new View.OnClickListener(){
            int counter=0;
            @Override
            public void onClick(View v){
                if (counter==0){
                    donotknowinformationsurveyresult.setBackgroundResource(R.drawable.buttoncheck);
                    editsavings.setHintTextColor(Color.parseColor("#000000"));
                    editsavings.setBackgroundColor(Color.parseColor("#E6A9A9"));
                    editsavings.setEnabled(false);
                    counter=1;
                }
                else{
                    donotknowinformationsurveyresult.setBackgroundResource(R.drawable.bgbtngreen);
                    editsavings.setHintTextColor(Color.parseColor("#696969"));
                    editsavings.setBackgroundColor(Color.parseColor("#F5FAFA"));
                    editsavings.setEnabled(true);
                    counter=0;
                }
            }
        });


        oinkoink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editsavings.getText().toString().trim().equalsIgnoreCase("") && editsavings.isEnabled())
                {
                    editsavings.setError("Input (Even if it's 0!)");

                }
                else {
                final TypeWriter oinkoinktext=(TypeWriter) findViewById(R.id.oinkoinktext);
                oinkoinktext.setText("");
                oinkoinktext.setCharacterDelay(50);
                oinkoinktext.animatedText("Oink! Oink!");


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            if(editsavings.isEnabled()){
                                orginaluser.setSavings(Float.parseFloat(editsavings.getText().toString()));
                            }
                            else{
                                orginaluser.setSavings(0);
                            }
                            Intent a = new Intent(surveryresultinformation.this, surveyresultsubscription.class);
                            a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                            a.putExtra("userObject", orginaluser);
                            startActivity(a);

                    }
                }, 1000);}
            }
        });
    }
    public String howmuch(int age){
             String result = "";
            if(age<=25){
                result= "Nothing! If you're not in debt\n you are doing well!";
            }
            else  if (age>25 && age<=30){
                result= "$6000 to $12000 ";
            }

            else  if (age>30 && age<=40){
                result= "$14000 to $28000 ";
            }
            else  if (age>40 && age<=60){
                result= "$25000 to $36000 ";
            }
            else  if (age>60){
                result = "Enough to retire!";
            }
            return result;
    }
}