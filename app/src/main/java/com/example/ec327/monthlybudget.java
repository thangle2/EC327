package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class monthlybudget extends AppCompatActivity {
    Button nextmonthlybudget, backmonthlybudget;
    EditText editmonthlybudget;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthlybudget);

        //constructor
        nextmonthlybudget = findViewById(R.id.nextmonthlybudget);
        editmonthlybudget = findViewById(R.id.editmonthlybudget);
        backmonthlybudget = findViewById(R.id.backmonthlybudget);
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        if (orginaluser.getMonthlyIncome() > 0) {
            editmonthlybudget.setText(Float.toString(orginaluser.getMonthlyIncome()));
        }


        editmonthlybudget.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editmonthlybudget.setText("$");
                    editmonthlybudget.setSelection(1);
                }
            }
        });
        nextmonthlybudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editmonthlybudget.getText().toString().trim().equalsIgnoreCase("")) {
                    editmonthlybudget.setError("Input");
                } else {
                    orginaluser.setMonthlyIncome(Float.parseFloat(editmonthlybudget.getText().toString().substring(1)));
                    Intent a = new Intent(monthlybudget.this, monthlybill.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    a.putExtra("userObject", orginaluser);
                    startActivity(a);

                }
            }
        });
        backmonthlybudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent c = new Intent(monthlybudget.this, information.class);
                c.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                c.putExtra("userObject", orginaluser);
                startActivity(c);


            }
        });
    }
}