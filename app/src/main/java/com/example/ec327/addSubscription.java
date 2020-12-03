package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addSubscription extends AppCompatActivity {                //

    TextView displayvalue;
    Button submitInvestment, addreturnhome;
    EditText typeinput, amountinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinvestment);

        submitInvestment = findViewById(R.id.submitSubscription);
        typeinput = findViewById(R.id.subscriptionInput);
        amountinput = findViewById(R.id.subscriptionAmountInput);
        displayvalue = findViewById(R.id.displayvalue);

        submitInvestment.setOnClickListener(new View.OnClickListener() {
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
                    calculateTotalSubscription();                 //Calculate % spending relative to income
                }

                //next screen
                /*else {
                    Intent a = new Intent(addspending.this, .class);
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(a);
                }*/

                //display % of daily amount

            }
        });
        addreturnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(addSubscription.this, Home.class);
                c.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(c);
            }
        });
    }

    private void calculateTotalSubscription() {
        //get entered texts from the amountinput
        Double addedInvestment = Double.parseDouble(amountinput.getText().toString());
        Double totalMonthlyInvestments = 0.00; // use financials function
        Double total = totalMonthlyInvestments + addedInvestment;

        displayvalue.setText(String.valueOf("Your new total amount of monthly investments is " + total));
    }
}
