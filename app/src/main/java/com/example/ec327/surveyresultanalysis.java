package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class surveyresultanalysis extends AppCompatActivity {
    TypeWriter submitresultanalysistext;
    Button keepit,changeit,submitpercentage;
    EditText percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyresultanalysis);
        submitresultanalysistext=findViewById(R.id.submitresultanalysistext);
        keepit=findViewById(R.id.keepit);
        changeit=findViewById(R.id.changeit);
        percentage=findViewById(R.id.percentage);
        percentage.setVisibility(View.INVISIBLE);
        submitpercentage=findViewById(R.id.submitpercentage);
        submitresultanalysistext.setText("");
        submitpercentage.setVisibility(View.INVISIBLE);
        submitresultanalysistext.setCharacterDelay(50);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");



        String result="Based on your survey\nYour monthly budget to save 10% of your income:\n$" + Float.toString(orginaluser.monthlyBudget(10))
                +"\n\nWhich comes out to be\n$"+ orginaluser.weeklyBudget() +" weekly\n\n Do you want to change the amount you want to save?";
        submitresultanalysistext.animatedText(result);
        keepit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(surveyresultanalysis.this, Home.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                orginaluser.setFirstday();
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
        changeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeit.setVisibility(View.INVISIBLE);
                submitpercentage.setVisibility(View.VISIBLE);
                percentage.setVisibility(View.VISIBLE);
            }
        });
        submitpercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(percentage.getText().toString().equalsIgnoreCase("")){
                    percentage.setError("Input!");
                }
                else{
                    orginaluser.monthlyBudget(Float.parseFloat(percentage.getText().toString()));
                    submitresultanalysistext.setText("");
                    String result="Based on your survey\nYour monthly budget to save "+ percentage.getText().toString()+"% of your income:\n$" + Float.toString(orginaluser.monthlyBudget(Float.parseFloat(percentage.getText().toString())))
                            +"\n\nWhich comes out to be\n$"+ orginaluser.weeklyBudget() +" weekly\n\n Do you want to change the amount you want to save?";
                    submitresultanalysistext.animatedText(result);
                    percentage.setVisibility(View.INVISIBLE);
                    submitpercentage.setVisibility(View.INVISIBLE);
                    changeit.setVisibility(View.VISIBLE);
                }
            }
        });



    }

}