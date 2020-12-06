package com.example.ec327;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class weeklygroceries extends AppCompatActivity {

    Button buttongro, backgro;
    EditText editgro;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeklygroceries);

        buttongro = findViewById(R.id.buttongro);
        backgro = findViewById(R.id.backgro);
        editgro = findViewById(R.id.editgro);

        editgro.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editgro.setText("$");
                    editgro.setSelection(1);
                }
            }
        });
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");
        if (orginaluser.getWeeklyGroceries() > 0) {
            editgro.setText(Float.toString(orginaluser.getWeeklyGroceries()));
        }
        buttongro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editgro.getText().toString().substring(1).trim().equalsIgnoreCase("")) {
                    editgro.setError("Input");

                } else if (Float.parseFloat(editgro.getText().toString().substring(1)) < 0) {
                    editgro.setError("No negative!");
                } else {
                    Intent a = new Intent(weeklygroceries.this, weektransport.class);
                    orginaluser.setWeeklyGroceries(Float.parseFloat(editgro.getText().toString().substring(1)));
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    a.putExtra("userObject", orginaluser);
                    startActivity(a);
                }
            }
        });
        backgro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(weeklygroceries.this, investment.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }
}