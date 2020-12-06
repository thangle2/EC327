package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addspending extends AppCompatActivity {                //

    TextView displayvalue;
    Button submitspending, addreturnhome;
    EditText typeinput, amountinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addspending);

        submitspending = findViewById(R.id.submitspending);
        typeinput = findViewById(R.id.typeinput);
        amountinput = findViewById(R.id.amountinput);
        displayvalue = findViewById(R.id.displayvalue);
        addreturnhome = findViewById(R.id.addreturnhome);


        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        amountinput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    amountinput.setText("$");
                    amountinput.setSelection(1);
                }
            }
        });
        submitspending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking user input: Spending Type
                if (typeinput.getText().toString().trim().equalsIgnoreCase("")) {
                    typeinput.setError("Please enter a type");
                }
                //checking user input: Amount
                else if (amountinput.getText().toString().trim().equalsIgnoreCase("")) {
                    amountinput.setError("Remember to enter a number!");
                } else {
                    orginaluser.setWeeklySpending(typeinput.getText().toString(), Float.parseFloat(amountinput.getText().toString().substring(1)));
                    calculatepercentage(orginaluser);         //Calculate % spending relative to daily limit

                }
            }
        });
        addreturnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(addspending.this, Home.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }

    private void calculatepercentage(Financials financials) {
        //get entered texts from the amountinput
        double num1 = Double.parseDouble(amountinput.getText().toString().substring(1));
        //do the calculation
        double num2 = (double) financials.weeklyBudget(); //num2: number from daily limit
        double calculated = (num1 / num2) * (double) 100.0;
        //display value on screen.
        displayvalue.setText(String.valueOf("You spend " + round(calculated, 2) + "% of your weekly amount"));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}