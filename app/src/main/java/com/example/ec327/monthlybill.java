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

  Button buttonbill, backbill, buttonsubmit;
  EditText monthlybillsnames, monthbillsvalue;
  TypeWriter textviewbill;
  EditText[] amountedit = new EditText[2];

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_monthlybill);

    //constructor
    buttonsubmit = findViewById(R.id.buttonsubmit);
    buttonbill = findViewById(R.id.buttonbill);
    backbill = findViewById(R.id.backbill);
    monthbillsvalue = findViewById(R.id.monthbillsvalue);
    monthlybillsnames = findViewById(R.id.monthlybillsnames);
    amountedit[0] = monthbillsvalue;
    amountedit[1] = monthlybillsnames;
    textviewbill = findViewById(R.id.textviewbill);

    Intent i = getIntent();
    Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
    monthbillsvalue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          monthbillsvalue.setText("$");
          monthbillsvalue.setSelection(1);
        }
      }
    });

    buttonsubmit.setOnClickListener(new View.OnClickListener() {
      int counter = 0;

      @RequiresApi(api = Build.VERSION_CODES.N)
      @Override
      public void onClick(View v) {
        if (!checkinput(amountedit)) {
          for (EditText editText : amountedit) {
            if (editText.getText().toString().trim().equalsIgnoreCase("")) {
              editText.setError("Input");
            }
          }
        } else if (orginaluser.subscription.size() >= 10) {
          monthlybillsnames.setError("Max: 10!");
        } else if (already(orginaluser, monthlybillsnames.getText().toString().toLowerCase())
            && counter == 0) {
          monthlybillsnames.setError("Already Input! Press again in resubmit!");
          counter = 1;
        } else if (already(orginaluser, monthlybillsnames.getText().toString().toLowerCase())
            && counter == 1) {
          String result =
              "Bill: " + monthlybillsnames.getText().toString() + "\nValue: $" + monthbillsvalue
                  .getText().toString().substring(1);
          textviewbill.setText(result);
          orginaluser.setBills(monthlybillsnames.getText().toString().toLowerCase(),
              Float.parseFloat(monthbillsvalue.getText().toString().substring(1)));
          monthlybillsnames.setError(null);
          monthlybillsnames.setText("");
          monthbillsvalue.setText("");
          counter = 0;
        } else {
          String result =
              "Bill: " + monthlybillsnames.getText().toString() + "\nValue: $" + monthbillsvalue
                  .getText().toString().substring(1);
          textviewbill.setText(result);
          orginaluser.setBills(monthlybillsnames.getText().toString().toLowerCase(),
              Float.parseFloat(monthbillsvalue.getText().toString().substring(1)));
          monthlybillsnames.setText("");
          monthbillsvalue.setText("");


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

  public boolean already(Financials orginaluser, String string) {
    for (Map.Entry mapElement : orginaluser.bills.entrySet()) {
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