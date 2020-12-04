package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class surveyresultanalysis extends AppCompatActivity {
    TypeWriter submitresultanalysistext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyresultanalysis);
        submitresultanalysistext=findViewById(R.id.submitresultanalysistext);
        submitresultanalysistext.setText("");
        submitresultanalysistext.setCharacterDelay(50);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        String result="Based on your survey\nIt looks like your monthly budget to save 10%" + Float.toString(orginaluser.monthlyBudget());
        submitresultanalysistext.animatedText(result);
    }
}