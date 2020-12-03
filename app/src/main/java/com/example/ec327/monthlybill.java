package com.example.ec327;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Map;

public class monthlybill extends AppCompatActivity {
    Button buttonbill, backbill,buttonsubmit;
    EditText monthlybillsnames,monthbillsvalue;
    TypeWriter textviewbill;
    EditText[] amountedit = new EditText[2];

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlybill);

        //constructor
        buttonsubmit=findViewById(R.id.buttonsubmit);
        buttonbill = findViewById(R.id.buttonbill);
        backbill = findViewById(R.id.backbill);
        monthbillsvalue=findViewById(R.id.monthbillsvalue);
        monthlybillsnames=findViewById(R.id.monthlybillsnames);
        amountedit[0]=monthbillsvalue;
        amountedit[1]=monthlybillsnames;
        textviewbill=findViewById(R.id.textviewbill);

        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        monthbillsvalue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    monthbillsvalue.setText("$");
                    monthbillsvalue.setSelection(1);
                }
            }
        });

        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            int counter=1;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(!checkinput(amountedit)){
                    for(int i=0;i<amountedit.length;i++){
                        if(amountedit[i].getText().toString().trim().equalsIgnoreCase("")){
                            amountedit[i].setError("Input");
                        }
                    }
                }
                else if(already(orginaluser,monthlybillsnames.getText().toString().toLowerCase()) && counter==0){
                    counter=1;
                    monthlybillsnames.setError("Already input! Submit again if correct");


                }
                else{
                    counter=0;
                    String result="Name: "+monthlybillsnames.getText().toString()+"\nValue: $"+monthbillsvalue.getText().toString().substring(1);
                    orginaluser.setBills(monthlybillsnames.getText().toString(),Float.parseFloat(monthbillsvalue.getText().toString().substring(1)));
                    textviewbill.setText(result);
                    for(int i=0;i<amountedit.length;i++){
                            amountedit[i].setText("");
                            amountedit[i].setError(null);
                    }

                }
            }
        });
        buttonbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(monthlybill.this, monthlysubs.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
        backbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(monthlybill.this, monthlybudget.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });


    }

    public void buttonreset(Button button, EditText edittext, String string, Drawable original) {
        button.setBackgroundResource(R.drawable.bgbtngreen);
        edittext.setHint(string);
        edittext.setHintTextColor(Color.parseColor("#696969"));
        edittext.setBackgroundColor(Color.parseColor("#F5FAFA"));
        edittext.setEnabled(true);
        edittext.setBackgroundDrawable(original);
    }

    public void buttonpressed(Button button, EditText edittext, String string) {

        button.setBackgroundResource(R.drawable.buttoncheck);
        edittext.setHint(string);
        edittext.setHintTextColor(Color.parseColor("#000000"));
        edittext.setBackgroundColor(Color.parseColor("#E6A9A9"));
        edittext.setEnabled(false);

    }
    public boolean already(Financials financials, String string){
        for (Map.Entry mapElement : financials.bills.entrySet()) {
            String name = (String) mapElement.getKey();
            float value = (float) mapElement.getValue();
            if(name==string){
                return false;
            }
        }
        return  true;

    }

    public boolean checkinput(EditText[] arrayinput) {
        for (int i = 0; i < arrayinput.length; i++) {
            if (arrayinput[i].getText().toString().trim().equalsIgnoreCase("")) {
                return false;
            }
        }
        return true;

    }
}