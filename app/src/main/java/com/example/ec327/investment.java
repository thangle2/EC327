package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class investment extends AppCompatActivity {
    Button buttoninvestment,backinvestment,submitinvestment;
    EditText editinvestmentname,editinvestmentvalue;
    EditText[] inputamountz=new EditText[2];
    TextView textminiinvestment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        buttoninvestment=findViewById(R.id.buttoninvestment);
        backinvestment=findViewById(R.id.backinvesment);
        submitinvestment=findViewById(R.id.submitinvestment);
        editinvestmentname=findViewById(R.id.editinvestmentname);
        inputamountz[0]=editinvestmentname;
        editinvestmentvalue=findViewById(R.id.editinvestmentvalue);
        inputamountz[1]=editinvestmentvalue;
        textminiinvestment=findViewById(R.id.textminiinvestment);

        Intent i=getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        submitinvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkinput(inputamountz)){
                    for (EditText editText : inputamountz) {
                        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                            editText.setError("Input");
                        }
                    }
                }
                else{
                    String result="Investment: "+ editinvestmentname.getText().toString()+ "\nValue: "+ editinvestmentvalue.getText().toString();
                    textminiinvestment.setText(result);
                    orginaluser.setInvestment(editinvestmentname.getText().toString(),Float.parseFloat(editinvestmentvalue.getText().toString()));


                }
            }
        });


        buttoninvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(investment.this, weeklygroceries.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject",orginaluser);
                startActivity(a);
            }
        });
        backinvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(investment.this, monthlysubs.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject",orginaluser);
                startActivity(a);
            }
        });
    }
    public boolean checkinput(EditText[] arrayinput){
        for(int i=0; i<arrayinput.length;i++){
            if(arrayinput[i].getText().toString().trim().equalsIgnoreCase("")){
                return false;
            }
        }
        return true;

    }
}