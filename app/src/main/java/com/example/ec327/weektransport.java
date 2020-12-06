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

public class weektransport extends AppCompatActivity {

    Button buttontran, backtran;
    EditText edittran;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weektransport);

        buttontran = findViewById(R.id.buttontran);
        backtran = findViewById(R.id.backtran);
        edittran = findViewById(R.id.edittran);
        edittran.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    edittran.setText("$");
                    edittran.setSelection(1);
                }
            }
        });
        Intent i = getIntent();
        Financials orginaluser = (Financials) i.getSerializableExtra("userObject");

        if (orginaluser.getTransportation() > 0) {
            edittran.setText(Float.toString(orginaluser.getTransportation()));
        }
        buttontran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittran.getText().toString().substring(1).trim().equalsIgnoreCase("")) {
                    edittran.setError("Input");
                } else if (Float.parseFloat(edittran.getText().toString().substring(1)) < 0) {
                    edittran.setError("No negative!");
                } else {


                    Intent a = new Intent(weektransport.this, loadingsurvey.class);
                    orginaluser.setTransportation(Float.parseFloat(edittran.getText().toString().substring(1)));
                    a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    a.putExtra("userObject", orginaluser);
                    startActivity(a);
                }
            }
        });
        backtran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(weektransport.this, weeklygroceries.class);
                a.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                a.putExtra("userObject", orginaluser);
                startActivity(a);
            }
        });
    }
}