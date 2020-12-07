package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import static java.lang.Math.round;

public class surveyresultsubscription extends AppCompatActivity {

  TypeWriter textaddingsubscription, texttotalsubscription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_surveyresultsubscription);
    textaddingsubscription = findViewById(R.id.textaddingsubscription);
    texttotalsubscription = findViewById(R.id.texttotalsubscription);
    Intent i = getIntent();
    Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
    int size = orginaluser.subscription.size();
    String result1 = "";
    float total = (float) 0.0;
    for (Map.Entry mapElement : orginaluser.subscription.entrySet()) {
      String name = (String) mapElement.getKey();
      String output = name.substring(0, 1).toUpperCase() + name.substring(1);
      float value = (float) mapElement.getValue();
      total = total + value;
      result1 = result1 + (output + ": $" + Float.toString(value) + "\n");
    }

    int amount = orginaluser.subscription.size() * 75;
    textaddingsubscription.setText("");
    textaddingsubscription.setCharacterDelay(25);
    textaddingsubscription.animatedText(result1);
    textaddingsubscription.setHeight(amount);
    float amountoftime = ((float) result1.length() * 25) + 1000;
    double percentage = round(total / orginaluser.getMonthlyIncome() * 100, 2);
    String totalamount = "You spend: $" + Float.toString(total) + " per month!\n This is " + Double
        .toString(percentage) + "%\n of your income!\n";

    long amountmoretime = (long) (amountoftime + 4500);
    final Handler handler = new Handler();
    if (percentage <= 10) {
      totalamount = totalamount + "This is very good!";
    } else if (percentage < 10 && percentage >= 20) {
      totalamount = totalamount + "This is not bad, need some fixing!";
    } else if (percentage > 20) {
      totalamount = totalamount + "We need to cut down on this!";
    }
    String finalTotalamount = totalamount;
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        texttotalsubscription.setText("");
        texttotalsubscription.setCharacterDelay(25);
        texttotalsubscription.animatedText(finalTotalamount);
      }
    }, (long) amountoftime);

    final Handler handler2 = new Handler();
    handler2.postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent a = new Intent(surveyresultsubscription.this, surveyresultanalysis.class);
        a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        a.putExtra("userObject", orginaluser);
        startActivity(a);
      }
    }, (long) amountmoretime);

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