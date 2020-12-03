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
            float value = (float) mapElement.getValue();
            total = total + value;
            result1 = result1 + (name + ": $" + Float.toString(value) + "\n");
        }

        int amount = orginaluser.subscription.size() * 75;
        textaddingsubscription.setText("");
        textaddingsubscription.setCharacterDelay(50);
        textaddingsubscription.animatedText(result1);
        textaddingsubscription.setHeight(amount);
        float amountoftime = ((float) result1.length() * 50) + 1000;
        double percentage = roundAvoid(total / orginaluser.getMonthlyIncome() * 100, 2);
        String totalamount = "You spend: $" + Float.toString(total) + " per month!\n This is " + Double.toString(percentage) + "%\n of your income!\n";

        long amountmoretime = (long) (amountoftime + 5000);
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
                texttotalsubscription.setCharacterDelay(50);
                texttotalsubscription.animatedText(finalTotalamount);
            }
        }, (long) amountoftime);


    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}