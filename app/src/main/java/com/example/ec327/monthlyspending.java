package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class monthlyspending extends AppCompatActivity {

  TypeWriter showallspending;
  Button gobackhome;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_monthlyspending);
    showallspending = findViewById(R.id.showallspending);
    gobackhome = findViewById(R.id.gobackhome);
    Intent i = getIntent();
    Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
    StringBuilder result = new StringBuilder();
    if (orginaluser.allWeeklySpending.size() != 0) {
      result.append("\n");
      for (int z = 0; z < orginaluser.allWeeklySpending.size(); z++) {
        result.append("Week").append(Integer.toString(z + 1)).append(":\n");
        TreeMap<String, Float> buffer = orginaluser.allWeeklySpending.get(z);
        for (TreeMap.Entry<String, Float> entry : buffer.entrySet()) {
          String name = (String) entry.getKey();
          Float value = (Float) entry.getValue();
          result.append("-").append(name).append(" : $").append(Float.toString(round(value, 2)))
              .append("\n");
        }
      }
      showallspending.setCharacterDelay(25);
      showallspending.setText("");
      showallspending.animatedText(result);
    } else {
      showallspending.setCharacterDelay(25);
      showallspending.setText("");
      showallspending
          .animatedText("\n\n\nNothing to show so far!\nCome back when you after a week!");
    }
    gobackhome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent a = new Intent(monthlyspending.this, Home.class);
        a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        a.putExtra("userObject", orginaluser);
        startActivity(a);
      }
    });

  }

  public static float round(float d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
    return bd.floatValue();
  }
}