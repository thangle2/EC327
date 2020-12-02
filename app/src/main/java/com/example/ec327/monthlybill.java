package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class monthlybill extends AppCompatActivity {
    Button buttonbill,buttonhousepayment,buttonhouseinsurance,buttoncarpayment,buttonhealthinsurance,backbill;
    EditText edithousepayment,edithouseinsurance,editcarpayment,edithealthinsurance;
    EditText[] amountedit= new EditText[4];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlybill);

        //constructor
        buttonhousepayment=findViewById(R.id.buttonhousepayment);
        buttonhouseinsurance=findViewById(R.id.buttonhouseinsurance);
        buttoncarpayment=findViewById(R.id.buttoncarpayment);
        buttonhealthinsurance=findViewById(R.id.buttonhealthinsurance);
        buttonbill=findViewById(R.id.buttonbill);
        backbill=findViewById(R.id.backbill);
        edithousepayment=findViewById(R.id.edithousepayment);
        amountedit[0]=edithousepayment;
        edithouseinsurance=findViewById(R.id.editouseinsurance);
        amountedit[1]=edithouseinsurance;
        editcarpayment=findViewById(R.id.editcarpayment);
        amountedit[2]=editcarpayment;
        edithealthinsurance=findViewById(R.id.edithealthinsurance);
        amountedit[3]=edithealthinsurance;
        Drawable original=edithouseinsurance.getBackground();

        Intent i=getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        backbill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent a= new Intent(monthlybill.this,monthlybudget.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject",orginaluser);
                startActivity(a);
            }
        });


    }
    public void buttonreset( Button button, EditText edittext, String string,Drawable original){
        button.setBackgroundResource(R.drawable.bgbtngreen);
        edittext.setHint(string);
        edittext.setHintTextColor(Color.parseColor("#696969"));
        edittext.setBackgroundColor(Color.parseColor("#F5FAFA"));
        edittext.setEnabled(true);
        edittext.setBackgroundDrawable(original);
    }
    public void buttonpressed (Button button, EditText edittext, String string){

        button.setBackgroundResource(R.drawable.buttoncheck);
        edittext.setHint(string);
        edittext.setHintTextColor(Color.parseColor("#000000"));
        edittext.setBackgroundColor(Color.parseColor("#E6A9A9"));
        edittext.setEnabled(false);

    }
    public boolean checkinput(EditText[] arrayinput){
        for(int i=0; i<arrayinput.length;i++){
            if(arrayinput[i].getText().toString().trim().equalsIgnoreCase("") && arrayinput[i].isEnabled()){
                return false;
            }
        }
        return true;

    }
}