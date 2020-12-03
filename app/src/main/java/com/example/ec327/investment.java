package com.example.ec327;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
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
import java.util.Map;

public class investment extends AppCompatActivity {
    Button buttoninvestment, backinvestment, submitinvestment;
    EditText editinvestmentname, editinvestmentvalue;
    EditText[] inputamountz = new EditText[2];
    TextView textminiinvestment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        buttoninvestment = findViewById(R.id.buttoninvestment);
        backinvestment = findViewById(R.id.backinvesment);
        submitinvestment = findViewById(R.id.submitinvestment);
        editinvestmentname = findViewById(R.id.editinvestmentname);
        inputamountz[0] = editinvestmentname;
        editinvestmentvalue = findViewById(R.id.editinvestmentvalue);
        inputamountz[1] = editinvestmentvalue;
        textminiinvestment = findViewById(R.id.textminiinvestment);

        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");


        editinvestmentvalue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    editinvestmentvalue.setText("$");
                    editinvestmentvalue.setSelection(1);
                }
            }
        });
        submitinvestment.setOnClickListener(new View.OnClickListener() {
            int counter = 0;

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!checkinput(inputamountz)) {
                    for (EditText editText : inputamountz) {
                        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                            editText.setError("Input");
                        }
                    }
                } else if (orginaluser.investment.size() >= 10) {
                    editinvestmentname.setError("Max: 10!");
                } else if (already(orginaluser, editinvestmentname.getText().toString().toLowerCase()) && counter == 0) {
                    editinvestmentname.setError("Already Input! Press again in resubmit!");
                    counter = 1;
                } else if (already(orginaluser, editinvestmentname.getText().toString().toLowerCase()) && counter == 1) {
                    String result = "Subscription: " + editinvestmentname.getText().toString() + "\nValue: $" + editinvestmentvalue.getText().toString().substring(1);
                    textminiinvestment.setText(result);
                    orginaluser.setInvestment(editinvestmentname.getText().toString().toLowerCase(), Float.parseFloat(editinvestmentvalue.getText().toString().substring(1)));
                    editinvestmentname.setError(null);
                    editinvestmentname.setText("");
                    editinvestmentvalue.setText("");
                    counter = 0;
                } else {
                    String result = "Investment: " + editinvestmentname.getText().toString() + "\nValue: $" + editinvestmentvalue.getText().toString().substring(1);
                    textminiinvestment.setText(result);
                    orginaluser.setInvestment(editinvestmentname.getText().toString().toLowerCase(), Float.parseFloat(editinvestmentvalue.getText().toString().substring(1)));
                    editinvestmentname.setText("");
                    editinvestmentvalue.setText("");


                }
            }
        });


        buttoninvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(investment.this, weeklygroceries.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
        backinvestment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(investment.this, monthlysubs.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }

    public boolean already(Financials orginaluser, String string) {
        for (Map.Entry mapElement : orginaluser.investment.entrySet()) {
            String name = (String) mapElement.getKey();
            if (name.equals(string)) {
                return true;
            }


        }
        return false;

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