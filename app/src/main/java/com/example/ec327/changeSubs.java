package com.example.ec327;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TreeMap;

public class changeSubs extends AppCompatActivity {

  TextView displayvalue;
  Button submitSub, addreturnhomesub;
  EditText typeinput, amountinput;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_subs);
    submitSub = findViewById(R.id.submitSubscription12);
    typeinput = findViewById(R.id.subscriptionInput);
    amountinput = findViewById(R.id.subscriptionAmountInput);
    displayvalue = findViewById(R.id.displayvalue);
    addreturnhomesub = findViewById(R.id.addreturnhomesubs);
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
    addreturnhomesub.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(changeSubs.this, profile.class);
        a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        a.putExtra("userObject", orginaluser);
        startActivity(a);
      }

    });
    submitSub.setOnClickListener(new View.OnClickListener() {
      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override
      public void onClick(View v) {
        if (typeinput.getText().toString().trim().equalsIgnoreCase("")) {
          typeinput.setError("Please enter a type");
        }
        //checking user input: Amount
        else if (amountinput.getText().toString().substring(1).trim().equalsIgnoreCase("")) {
          amountinput.setError("Remember to enter a number!");
        } else {
          orginaluser.setSubscription(typeinput.getText().toString().toLowerCase(),
                  Float.parseFloat(amountinput.getText().toString().substring(1)));
          orginaluser.monthlyBudget(orginaluser.getPercentage());
          orginaluser.weeklyBudget();
          calculateTotalSubs(orginaluser);                 //Calculate % spending relative to income
          amountinput.setText("");
          typeinput.setText("");
        }

      }

    });
  }

  private void calculateTotalSubs(Financials financials) {
    //get entered texts from the amountinput
    Double addedBill = Double.parseDouble(amountinput.getText().toString().substring(1));
    Double totalBills = (Double) 0.0; // use financials function

    for (TreeMap.Entry<String, Float> entry : financials.subscription.entrySet()) {
      Float value = (Float) entry.getValue();
      totalBills += Double.valueOf(value);

    }
    Double total = totalBills + addedBill;

    displayvalue.setText(String.valueOf("Your new total amount of monthly Subscription is $" + round(total,2)));
  }
  public static double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }
}