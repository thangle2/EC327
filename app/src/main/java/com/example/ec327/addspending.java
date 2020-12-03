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
                } else if (Float.parseFloat(amountinput.getText().toString()) < 0) {
                    amountinput.setError("No negative!");
                } else {
                    calculatepercentage();         //Calculate % spending relative to daily limit
                }
            }
        });
        addreturnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(addspending.this, Home.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(a);
            }
        });
    }
    private void calculatepercentage() {
        //get entered texts from the amountinput
        double num1 = Double.parseDouble(amountinput.getText().toString());
        //do the calculation
        double num2 = 2500.00; //num2: number from daily limit
        double calculated = (num1 / num2);
        //display value on screen.
        displayvalue.setText(String.valueOf("You spend " + calculated + "% of your daily amount"));
    }
}