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


        if(orginaluser.getHousingCost()>0){
            edithousepayment.setText(Float.toString(orginaluser.getHousingCost()));
        }
        if(orginaluser.getHomeInsurance()>0){
            edithouseinsurance.setText(Float.toString(orginaluser.getHomeInsurance()));
        }
        if(orginaluser.getCarPayment()>0){
            editcarpayment.setText(Float.toString(orginaluser.getCarPayment()));
        }
        if(orginaluser.getHealthInsurance()>0){
            edithealthinsurance.setText(Float.toString(orginaluser.getHealthInsurance()));
        }



        buttonhousepayment.setOnClickListener(new View.OnClickListener(){
            int counter=0;
            @Override
            public void onClick(View v){
                if (counter==1){
                    buttonreset(buttonhousepayment,edithousepayment,"House Payment",original);
                    counter=0;
                }
                else if(counter==0) {
                    buttonpressed(buttonhousepayment, edithousepayment, "No House Payment");
                    orginaluser.setHousingCost(0);
                    counter=1;
                }
            }
        });
        buttonhouseinsurance.setOnClickListener(new View.OnClickListener(){
            int counter=0;
            @Override
            public void onClick(View v){
                if (counter==1){
                    buttonreset(buttonhouseinsurance,edithouseinsurance,"House Insurance",original);
                    counter=0;
                }
                else if (counter==0) {
                    buttonpressed(buttonhouseinsurance, edithouseinsurance, "No House Insurance");
                    orginaluser.setHomeInsurance(0);
                    counter=1;
                }
            }
        });
        buttoncarpayment.setOnClickListener(new View.OnClickListener(){
            int counter=0;
            @Override
            public void onClick(View v){
                if (counter==1){
                    buttonreset(buttoncarpayment,editcarpayment,"Car Payment",original);
                    counter=0;
                }
                else if(counter==0) {
                    buttonpressed(buttoncarpayment, editcarpayment, "No Car Payment");
                    orginaluser.setCarPayment(0);
                    counter=1;
                }
            }
        });
        buttonhealthinsurance.setOnClickListener(new View.OnClickListener(){
            int healthinsurancecounter=0;
            @Override
            public void onClick(View v){
                if (healthinsurancecounter==1){
                    buttonreset(buttonhealthinsurance,edithealthinsurance,"Health Insurance",original);
                    healthinsurancecounter=0;
                }
                else if (healthinsurancecounter==0) {

                    buttonpressed(buttonhealthinsurance, edithealthinsurance, "No Health Insurance");
                    orginaluser.setHealthInsurance(0);
                    healthinsurancecounter=1;
                }
            }
        });

        buttonbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkinput(amountedit)) {
                    if (amountedit[0].isEnabled()) {
                        orginaluser.setHousingCost(Float.parseFloat(edithousepayment.getText().toString()));
                    }
                    if (amountedit[1].isEnabled()) {
                        orginaluser.setHomeInsurance(Float.parseFloat(edithouseinsurance.getText().toString()));
                    }
                    if (amountedit[2].isEnabled()) {
                        orginaluser.setCarPayment(Float.parseFloat(editcarpayment.getText().toString()));
                    }
                    if (amountedit[3].isEnabled()) {
                        orginaluser.setHealthInsurance(Float.parseFloat(edithealthinsurance.getText().toString()));
                    }
                    Intent a = new Intent(monthlybill.this, monthlysubs.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    a.putExtra("userObject", orginaluser);
                    startActivity(a);
                }
                else{
                    for(int i=0;i<amountedit.length;i++) {
                        if (amountedit[i].isEnabled() && amountedit[i].getText().toString().trim().equalsIgnoreCase("")) {
                            amountedit[i].setError("Please Input");
                        }
                        else if (Float.parseFloat(amountedit[i].getText().toString())<0){
                            amountedit[i].setError("No negative!");
                        }
                    }
                }
            }

        });
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